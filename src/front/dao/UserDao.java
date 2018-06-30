package front.dao;

import entity.User;

public interface UserDao {
    public User getUserByUserId(int user_id);
    public boolean getUserByUserName(String username);

    public User validateUser(User user);

    public boolean addUser(User user);

    public boolean editUser(User user);

}
