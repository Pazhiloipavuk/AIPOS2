package lab2.service.implementation;

import lab2.dao.UserDao;
import lab2.exception.ExceptionInLab;
import lab2.model.User;
import lab2.service.CommentService;
import lab2.service.DescriptionService;
import lab2.service.UserService;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    private DescriptionService descriptionService;

    private CommentService commentService;

    @Autowired
    public UserServiceImpl(UserDao userDao, DescriptionService descriptionService, CommentService commentService) {
        this.userDao = userDao;
        this.descriptionService = descriptionService;
        this.commentService = commentService;
    }

    @Override
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public List<User> findAll() {
        return IterableUtils.toList(userDao.findAll());
    }

    @Override
    public void delete(User user) {
        user.getComments().forEach(commentService::delete);
        user.getDescriptions().forEach(descriptionService::delete);
        userDao.delete(user);
    }

    @Override
    public void deleteById(Long id) {
        delete(findById(id).orElseThrow(ExceptionInLab::new));
    }
}