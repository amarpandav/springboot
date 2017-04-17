package com.example.controller;

import com.example.bean.LabelProperties;
import com.example.domain.Book;
import com.example.service.ReadingListRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import static org.hibernate.validator.internal.util.CollectionHelper.newArrayList;

@Controller
@RequestMapping("/")
public class MyController {

    private static final Logger logger = LoggerFactory.getLogger(MyController.class);

    @Autowired
    private Environment env;

    @Autowired
    private ReadingListRepository readingListRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    //TODO - books will come from DB
    private List<Book> books = newArrayList();

    @Autowired
    private LabelProperties labelProperties;

    @Value("${lbl.myNameIs}")
    private String myNameIs;

    private static final String MANIFEST_RESOURCE_NAME = "/META-INF/MANIFEST.MF";

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView viewApplication(HttpServletRequest request) {
        return prepareHomePage(request);
    }

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public ModelAndView homePage(HttpServletRequest request) {
        return prepareHomePage(request);
    }

    private ModelAndView prepareHomePage(HttpServletRequest request) {
        Map<String, Object> model = new HashMap<String, Object>();

        /*model.put("appTitle",env.name());
        model.put("contextPath", contextPath);
        model.put("versionMarker", VERSION_MARKER);
        model.put("componentVersion",componentVersion +" " + buildTime);
        model.put("userPid",userBean.getUserPid());
        model.put("iamLink",iamLink);

        userBean.getCurrentUser();
        model.put("userBean", userBean);*/


        List<String> activeProfiles = Arrays.asList(env.getActiveProfiles());

        model.put("activeProfile", activeProfiles.get(0));
        model.put("componentVersion", getManifestVersion(request));
        model.put("myNameIs", labelProperties.getMyNameIs());


        if (activeProfiles.contains("development")) {
            return new ModelAndView("/public/home_dev", model);
        }
        return new ModelAndView("/public/home", model);
    }

    private String getManifestVersion(HttpServletRequest request) {
        ServletContext servletContext = request.getServletContext();
        Manifest mf = new Manifest();
        try {
            //  InputStream is = this.getClass().getClassLoader().getResourceAsStream(MANIFEST_RESOURCE_NAME);
            InputStream is = servletContext.getResourceAsStream(MANIFEST_RESOURCE_NAME);
            //String v = this.getClass().getPackage().getImplementationVersion();
            if (is == null) {
                return "local";
            }

            mf.read(is);
            return mf.getMainAttributes().getValue(Attributes.Name.IMPLEMENTATION_VERSION);
        } catch (Exception ex) {
            // Log the exception, but carry on. No need to propagate the Exception.
            logger.error("Error getting manifest version for footer", ex);
        }
        return "";
    }


    @RequestMapping(value = "error", method = RequestMethod.GET)
    public String errorPage() {
        throw new IllegalStateException("Example to show how error page is shown.");
    }


    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String LoginPage() {
        return "/public/login";
    }

    @RequestMapping(value = "performLogin", method = RequestMethod.POST)
    public String performLogin(@ModelAttribute("username") String username, @ModelAttribute("password") String password, HttpSession session) {
        //TODO based on role go to adminPage or userPage.
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        session.setAttribute("username", userDetails.getUsername());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            return "/admin/adminPage";
        } else {
            return "/user/userPage";
        }

    }

    @RequestMapping(value = "admin", method = RequestMethod.GET)
    public String admin() {
        return "/admin/adminPage";
    }

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public String user() {
        return "/user/userPage";
    }

    @RequestMapping(value = "books", method = RequestMethod.GET)
    public String books(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUser = auth.getName();

        /*TODO List<Book> readingList =
                readingListRepository.findByReader(loggedInUser);*/
        if (books != null) {
            model.addAttribute("books", books);
        }
        return "/user/books";
    }

    @RequestMapping(value = "saveBook", method = RequestMethod.POST)
    public String saveBook(Model model, Book book) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUser = auth.getName();

        book.setReader(loggedInUser);

        //TODO service readingListRepository.save(book);
        books.add(book);
        model.addAttribute("books", books);

        return "/user/books";
    }


}