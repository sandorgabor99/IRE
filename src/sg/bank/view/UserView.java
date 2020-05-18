package sg.bank.view;

import org.primefaces.event.RowEditEvent;
import sg.bank.entity.Account;
import sg.bank.entity.Users;
import sg.bank.service.AccountService;
import sg.bank.service.UserService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;

@Named("userView")
@ViewScoped
public class UserView implements Serializable {

    @Inject
    private UserService service;

    @Inject
    private AccountService accountService;

    private ArrayList<Users> users;
    private ArrayList<Users> filteredUsers;

    private Users newUser = new Users();

    @PostConstruct
    public void init() {
        System.out.println("userService injected : " + service);
        users = service.getAllUsers();
    }

    public String addNewUser() {
        service.createUser(newUser);
        newUser = new Users();
        return "users?faces-redirect=true\"";
    }

    public void onRowEdit(RowEditEvent<Users> event) {
        FacesMessage msg = new FacesMessage("Felhasználó szerkesztve", event.getObject().getFullname());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        System.out.println(event.getObject().toString());
        service.updateUser(event.getObject());
    }

    public void onRowCancel(RowEditEvent<Users> event) {
        FacesMessage msg = new FacesMessage("Szerkesztés megszakítva", event.getObject().getFullname());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public ArrayList<Users> getUsers() {
        if (users == null) {
            users = service.getAllUsers();
        }
        return users;
    }


    public void setUsers(ArrayList<Users> users) {
        this.users = users;
    }

    public ArrayList<Users> getFilteredUsers() {
        return filteredUsers;
    }

    public void setFilteredUsers(ArrayList<Users> filteredUsers) {
        this.filteredUsers = filteredUsers;
    }

    public Users getNewUser() {
        return newUser;
    }

    public void setNewUser(Users newUser) {
        this.newUser = newUser;
    }
}
