package sgdevcamp.blog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sgdevcamp.blog.data.entity.Question;
import sgdevcamp.blog.data.entity.SiteUser;
import sgdevcamp.blog.data.repository.QuestionRepository;
import sgdevcamp.blog.dto.request.QuestionForm;
import sgdevcamp.blog.exception.DataNotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public Page<Question> getList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));

        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.questionRepository.findAll(pageable);
    }

    public Question getQuestion(Long id) {
        Optional<Question> question = questionRepository.findById(id);
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundException("question not found");
        }

    }

    public void create(String subject, String content, SiteUser siteUser) {
        Question q = new Question();
        q.setSubject(subject);
        q.setContent(content);
        q.setCreateDate(LocalDateTime.now());
        q.setSiteUser(siteUser);
        this.questionRepository.save(q);
    }

    public QuestionForm modify(QuestionForm questionForm, Long id) {
        Optional<Question> question = questionRepository.findById(id);
        if (question.isPresent()) {
            question.get().setSubject(questionForm.getSubject());
            question.get().setContent(questionForm.getContent());
            question.get().setModifyDate(LocalDateTime.now());
            this.questionRepository.save(question.get());
        }
        else {
            throw new DataNotFoundException("question not found");
        }

        questionForm.setSubject(question.get().getSubject());
        questionForm.setContent(question.get().getContent());

        return questionForm;
    }

    public void delete(Long id) {
        Optional<Question> question = questionRepository.findById(id);
        if (question.isPresent()) {
            this.questionRepository.delete(question.get());
        }
        else {
            throw new DataNotFoundException("question not found");
        }

    }

}