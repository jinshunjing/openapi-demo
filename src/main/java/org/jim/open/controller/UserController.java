package org.jim.open.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller
 *
 * Client needs to get the access token at first, by providing the id and secret:
 * curl -X POST -F 'grant_type=client_credentials' -F 'scope=trust'
 * -F 'client_id=645968e2bad447d78286724a135fa868' -F 'client_secret=61ccdbce2e15ee92c55019d4406eca38f2fb6b92'
 * http://127.0.0.1:18002/oauth/token
 *
 * @author author
 */
@RestController("UserController")
@RequestMapping(produces="application/json;charset=UTF-8")
@Slf4j
public class UserController {

    /**
     * Provide the access_token:
     * http://127.0.0.1:18002/v1/user/greeting?access_token=3e04896f-b24a-43cb-942b-c95ca403c69a
     *
     * @param servletRequest
     * @return
     */
    @GetMapping(value = {"/v1/user/greeting"})
    @ResponseBody
    public String greeting(HttpServletRequest servletRequest) {
        String uid = (String) servletRequest.getAttribute("uid");
        return "Hello, " + uid + "!";
    }

    /**
     * Must POST Form Data:
     * curl -X POST -F 'access_token=bba375ad-8707-4e31-9963-bf102c035f2d' http://127.0.0.1:18002/v1/user/bye
     *
     * @param servletRequest
     * @return
     */
    @PostMapping(value = {"/v1/user/bye"})
    @ResponseBody
    public String bye(HttpServletRequest servletRequest) {
        String uid = (String) servletRequest.getAttribute("uid");
        return "Bye, " + uid + "!";
    }

}
