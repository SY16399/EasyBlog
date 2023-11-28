package org.example.controller;


import com.alibaba.fastjson.JSONObject;
import org.example.pojo.MyUser;
import org.example.service.TokenService;
import org.example.service.UserService;
import org.example.tools.JsonResultObject;
import org.example.tools.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class UserController {
    @GetMapping("/")
    public String Strng(){
        return "1234566";
    }
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;

    @PostMapping("/register")
    public JsonResultObject register(@RequestBody MyUser user){
        return userService.register(user);
    }
    @PostMapping("/login")
    public JsonResultObject login(@RequestBody LoginUser loginUser){
        JsonResultObject result = userService.login(loginUser);
        if (result.getErroeMessage() != ""){
            return result;
        }else {
            String token = tokenService.getToken((MyUser) result.getData());
            JSONObject returnObject = new JSONObject();
            returnObject.put("token",token);
            result.setData(returnObject);
            return result;
        }
    }
}
