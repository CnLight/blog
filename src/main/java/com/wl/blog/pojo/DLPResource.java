package com.wl.blog.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DLPResource {
   private String ext;
     private String filename;
     private String size;
     private String utime;
     private String ctime;
     private String id;

}
