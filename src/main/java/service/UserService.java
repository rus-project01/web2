package service;

import model.User;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class UserService {

    public static UserService usik = new UserService();

    /* хранилище данных */
    private Map<Long, User> dataBase = Collections.synchronizedMap(new HashMap<>());
    /* счетчик id */
    private AtomicLong maxId = new AtomicLong(0);
    /* список авторизованных пользователей */
    private Map<Long, User> authMap = Collections.synchronizedMap(new HashMap<>());

    public static UserService data(){
        return usik;
    }
    Long i = 0L;

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>(dataBase.values());
        return list;
    }

    public User getUserById(Long id) {
        return dataBase.get(id);
    }

    public boolean addUser(User user) {
        if(isExistsThisUser(user) == false){
            user.setId(i++);
            dataBase.put(user.getId(), user);
            maxId.getAndIncrement();
            return true;
        }
        return false;
    }

    public void deleteAllUser() {
        for (int i = 0; i < dataBase.size(); i++) {
            dataBase.remove(i);
        }
        maxId.set(0);
    }

    public boolean isExistsThisUser(User user) {
        for (Long i = 0L; i < dataBase.size(); i++) {
            if(user.getEmail().equals(dataBase.get(i).getEmail())){
                return true;
            }
        } return false;
    }

    public List<User> getAllAuth() {
        List<User> te = new ArrayList<>(authMap.values());
        return te;
    }

    public boolean authUser(User user) {
        if(isExistsThisUser(user) == true){
            authMap.put(user.getId(), user);
            return true; //ошибка
        }
        return false;
    }

    public void logoutAllUsers() {
        for (int i = 0; i < authMap.size(); i++) {
            authMap.remove(i);
        }
    }

    public boolean isUserAuthById(Long id) {
        if(authMap.get(id) == null){
            return false;
        }else return true;
    }

}
