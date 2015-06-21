package kitapp.hska.de.kitapp.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by bwpc on 21.06.2015.
 */
public class AppUser implements Serializable {

    /*
   <======================= ATTRIBUTES =======================>
    */

    private Long id;
    private String email;
    private String name;
    private List<Kita> favorits;
    private Date dateCreated;

    /*
    <======================= CONSTRUCTORS =======================>
     */

    public AppUser() {

    }

    public AppUser(String email, String name, List<Kita> favorits, Date dateCreated) {
        this.email = email;
        this.name = name;
        this.favorits = favorits;
        this.dateCreated = dateCreated;
    }

    /*
    <======================= OVERRIDES =======================>
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AppUser)) return false;

        AppUser appUser = (AppUser) o;

        if (getId() != null ? !getId().equals(appUser.getId()) : appUser.getId() != null)
            return false;
        if (getEmail() != null ? !getEmail().equals(appUser.getEmail()) : appUser.getEmail() != null)
            return false;
        if (getName() != null ? !getName().equals(appUser.getName()) : appUser.getName() != null)
            return false;
        if (getFavorits() != null ? !getFavorits().equals(appUser.getFavorits()) : appUser.getFavorits() != null)
            return false;
        return !(getDateCreated() != null ? !getDateCreated().equals(appUser.getDateCreated()) : appUser.getDateCreated() != null);

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getFavorits() != null ? getFavorits().hashCode() : 0);
        result = 31 * result + (getDateCreated() != null ? getDateCreated().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", favorits=" + favorits +
                ", dateCreated=" + dateCreated +
                '}';
    }

    /*
    <======================= GETS & SETS =======================>
     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Kita> getFavorits() {
        return favorits;
    }

    public void setFavorits(List<Kita> favorits) {
        this.favorits = favorits;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
