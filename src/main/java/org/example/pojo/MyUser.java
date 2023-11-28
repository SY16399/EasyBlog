package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyUser {
    private int id;
    private String name;
    private String password;
    private String email;
    private int roleId;
    private String status;
    private int regTime;
}
