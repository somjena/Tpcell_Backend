package com.example.placement_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserService {

    @Autowired
    UsersRepository repo;
    @Autowired SettingsService settings;

    public void createAdmin() {
        if(repo.count()==0) {
            repo.save(new Users("admin","Administrator","admin",0,"Admin",true));
            settings.saveSettings(new Settings("CUTM","BBSR"));
        }
    }

    public Users findUser(String userid) {
        return repo.getById(userid);
    }

    public void deleteUser(int cid) {
        Users user=findByCid(cid);
        repo.delete(user);
    }

    public void AddUser(Users user) {
        repo.save(user);
    }

    public Users findByCid(int cid) {
        return repo.findByCid(cid);
    }

    public void ChangePwd(Users user) {
        Users u=repo.findById(user.getUserid()).get();
        u.setPwd(user.getPwd());
        repo.save(user);
    }

    public void updateUser(Users user) {
        repo.save(user);
    }

    public Users validate(String userid,String pwd,String role) {
        Optional<Users> uinfo=repo.findById(userid);
        if(uinfo.isPresent() && uinfo.get().getPwd().equals(pwd) && uinfo.get().isActive() && uinfo.get().getRole().equalsIgnoreCase(role)) {
            return uinfo.get();
        }
        return null;
    }

    public Users verify(String userid) {
        Optional<Users> uinfo=repo.findById(userid);
        if(uinfo.isPresent()) {
            return uinfo.get();
        }
        return null;
    }

    public List<Users> getAllUsers(){
        return repo.findAll(Sort.by(Sort.Direction.DESC, "createdon"));
    }
}
