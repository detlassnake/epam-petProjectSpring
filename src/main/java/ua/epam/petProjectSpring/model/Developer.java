package ua.epam.petProjectSpring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

//@Entity
@Table(name = "developer")
public class Developer extends GenericModel {

    @Column(name = "developer_name")
    private String name;

    @Column(name = "account_name")
    private String account;

    @Column(name = "skill_name")
    private String skill;

    public String getName() {
        return name;
    }

    public String getAccount() {
        return account;
    }

    public String getSkill() {
        return skill;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", account='" + account + '\'' +
                ", skill='" + skill + '\'' +
                '}';
    }
}
