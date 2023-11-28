package org.example.service;

import org.example.mapper.MyUserMapper;
import org.example.tools.ErrorEnum;
import org.example.tools.JsonResultObject;
import org.example.tools.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    MyUserMapper myUserMapper;
    public JsonResultObject doLogin(LoginUser loginUser){
        JsonResultObject result = new JsonResultObject();
        ErrorEnum enum1 = ErrorEnum.valueOf(ErrorEnum.class,"PASSWORD_OR_USERNAME_WRONG");
        result.setCode("200");
        result.setMessage("");
        result.setErrorCode("");
        result.setErroeMessage("");
        try{
            Integer userId = myUserMapper.doLogin(loginUser);
            if (userId == null){
                result.setErroeMessage("用户名或密码错误");
                result.setErrorCode(enum1.getErrorCode());
                result.setMessage(enum1.getErrorMsg());
            }else {
                result.setMessage("登录成功");
            }
        }catch (Exception e){
            result.setCode("500");
            result.setErrorCode("100211");
            result.setErroeMessage(e.getMessage());
        }
        return result;
    }
}
