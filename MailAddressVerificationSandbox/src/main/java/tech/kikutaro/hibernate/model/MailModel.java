package tech.kikutaro.hibernate.model;

import javax.validation.constraints.Email;

/**
 * メールモデル.
 * 
 * Bean Validation検証用のモデル
 * 
 * @author kikuta
 */
public class MailModel {
    
    @Email
    private String email;
    
    public MailModel(String email) {
        this.email = email;
    }
}
