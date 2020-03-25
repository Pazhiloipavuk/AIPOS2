package lab2.service.implementation;

import lab2.dao.CommentDao;
import lab2.exception.ExceptionInLab;
import lab2.model.Comment;
import lab2.service.CommentService;
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
public class CommentServiceImpl implements CommentService {

    private CommentDao commentDao;

    @Autowired
    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public Comment save(Comment entity) {
        return commentDao.save(entity);
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentDao.findById(id);
    }

    @Override
    public List<Comment> findAll() {
        return IterableUtils.toList(commentDao.findAll());
    }

    @Override
    public void delete(Comment entity) {
        commentDao.delete(entity);
    }

    @Override
    public void deleteById(Long id) {
        delete(findById(id).orElseThrow(ExceptionInLab::new));
    }
}
