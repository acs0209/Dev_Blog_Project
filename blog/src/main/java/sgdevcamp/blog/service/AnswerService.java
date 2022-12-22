package sgdevcamp.blog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sgdevcamp.blog.data.entity.Answer;
import sgdevcamp.blog.data.entity.Question;
import sgdevcamp.blog.data.entity.SiteUser;
import sgdevcamp.blog.data.repository.AnswerRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    public void create(Question question, String content, SiteUser user) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setQuestion(question);
        answer.setCreateDate(LocalDateTime.now());
        answer.setSiteUser(user);
        this.answerRepository.save(answer);

    }

}