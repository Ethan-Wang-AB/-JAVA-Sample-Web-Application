/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 845593
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
    , @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email")
    , @NamedQuery(name = "User.findByActive", query = "SELECT u FROM User u WHERE u.active = :active")
    , @NamedQuery(name = "User.findByFirstName", query = "SELECT u FROM User u WHERE u.firstName = :firstName")
    , @NamedQuery(name = "User.findByLastName", query = "SELECT u FROM User u WHERE u.lastName = :lastName")
    , @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password")
    , @NamedQuery(name = "User.findBySalt", query = "SELECT u FROM User u WHERE u.salt = :salt")
    , @NamedQuery(name = "User.findByResetPasswordUuid", query = "SELECT u FROM User u WHERE u.resetPasswordUuid = :resetPasswordUuid")
    , @NamedQuery(name = "User.findByDirectLoginUuid", query = "SELECT u FROM User u WHERE u.directLoginUuid = :directLoginUuid")
    , @NamedQuery(name = "User.findByAuthenUuid", query = "SELECT u FROM User u WHERE u.authenUuid = :authenUuid")
    , @NamedQuery(name = "User.findByRegistrationCode", query = "SELECT u FROM User u WHERE u.registrationCode = :registrationCode")
    , @NamedQuery(name = "User.findByPhotoPath", query = "SELECT u FROM User u WHERE u.photoPath = :photoPath")
    , @NamedQuery(name = "User.findByDisplay", query = "SELECT u FROM User u WHERE u.display = :display")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "active")
    private boolean active;
    @Basic(optional = false)
    @Column(name = "first_name")
    private String firstName;
    @Basic(optional = false)
    @Column(name = "last_name")
    private String lastName;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Column(name = "salt")
    private String salt;
    @Column(name = "reset_password_uuid")
    private String resetPasswordUuid;
    @Column(name = "direct_login_uuid")
    private String directLoginUuid;
    @Column(name = "authen_uuid")
    private String authenUuid;
    @Column(name = "registration_code")
    private String registrationCode;
    @Column(name = "photoPath")
    private String photoPath;
    @Basic(optional = false)
    @Column(name = "display")
    private boolean display;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Item> itemList;
    @JoinColumn(name = "companyID", referencedColumnName = "company_id")
    @ManyToOne(optional = false)
    private Company companyID;
    @JoinColumn(name = "role", referencedColumnName = "role_id")
    @ManyToOne(optional = false)
    private Role role;

    public User() {
    }

    public User(String email) {
        this.email = email;
    }

    public User(String email, boolean active, String firstName, String lastName, String password, boolean display) {
        this.email = email;
        this.active = active;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.display = display;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getResetPasswordUuid() {
        return resetPasswordUuid;
    }

    public void setResetPasswordUuid(String resetPasswordUuid) {
        this.resetPasswordUuid = resetPasswordUuid;
    }

    public String getDirectLoginUuid() {
        return directLoginUuid;
    }

    public void setDirectLoginUuid(String directLoginUuid) {
        this.directLoginUuid = directLoginUuid;
    }

    public String getAuthenUuid() {
        return authenUuid;
    }

    public void setAuthenUuid(String authenUuid) {
        this.authenUuid = authenUuid;
    }

    public String getRegistrationCode() {
        return registrationCode;
    }

    public void setRegistrationCode(String registrationCode) {
        this.registrationCode = registrationCode;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public boolean getDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }

    @XmlTransient
    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public Company getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Company companyID) {
        this.companyID = companyID;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (email != null ? email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.User[ email=" + email + " ]";
    }
    
}
