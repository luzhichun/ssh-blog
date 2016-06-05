package com.blog.ssh.model.vo;

import org.hibernate.bytecode.internal.javassist.FieldHandled;
import org.hibernate.bytecode.internal.javassist.FieldHandler;

import javax.persistence.*;

/**
 * Created by wy on 2016/6/5 0005.
 */
@Entity
public class Test implements FieldHandled {

    @Id
    private String uid;

    private String uname;

    private int age;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] img;

    private FieldHandler fieldHandler;

    public Test() {
    }

    // getter() and setter() of uid, uname, age

    public byte[] getImg() {
        // if User user = new User() then fieldHandler is null
        // if User user = entityManager.find(User.class, "001") then fieldHandler is not null
        if(img != null) {
            return img;
        }

        if (fieldHandler != null) {
            return (byte[]) fieldHandler.readObject(this, "img", img);
        } else {
            return null;
        }
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public void setFieldHandler(FieldHandler fieldHandler) {
        this.fieldHandler = fieldHandler;
    }

    public FieldHandler getFieldHandler() {
        return fieldHandler;
    }
}
