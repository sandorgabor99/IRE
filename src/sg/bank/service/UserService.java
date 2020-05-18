package sg.bank.service;

import org.hibernate.loader.collection.OneToManyJoinWalker;
import sg.bank.dto.UsersDto;
import sg.bank.entity.Users;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Random;

@Named
@ApplicationScoped
public class UserService {

    private UsersDto usersDto = new UsersDto();

    public UserService() {
    }

    public ArrayList<Users> getAllUsers() {
        return usersDto.getAllUsers();
    }

    public void createUser(Users entity) {
        entity.setActiveUser(true);
        entity.setHasAccount(false);
        int id = Integer.parseInt(getRandomNumberString());
        if(usersDto.getUserById(id) == null){
            entity.setId(id); //generated id setting
            System.out.println(entity.toString());
            usersDto.createNewUser(entity);
        }

    }

    public void updateUser(Users entity) {
        usersDto.updateUser(entity);
    }

    public void deleteUser(Users entity) {
        usersDto.deleteUser(entity);
    }

    public Users getUserById(int id) {
        return usersDto.getUserById(id);
    }

    public static String getRandomNumberString() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }
}
