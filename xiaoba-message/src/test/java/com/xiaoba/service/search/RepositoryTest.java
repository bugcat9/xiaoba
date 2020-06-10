package com.xiaoba.service.search;

import com.xiaoba.entity.EssayIndex;
import com.xiaoba.entity.Question;
import com.xiaoba.entity.QuestionIndex;
import com.xiaoba.repository.EssayRepository;
import com.xiaoba.repository.QuestionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RepositoryTest {
    @Autowired
    EssayRepository essayRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Test
    public void testEssay(){
        EssayIndex essayIndex=new EssayIndex();
        essayIndex.setEssayId(99);
        essayIndex.setEssayTitle("========================");
        System.out.println("-------------------------");
        essayRepository.save(essayIndex);
        essayRepository.deleteById(99);
    }



    @Test
    public void testQuestion(){
        QuestionIndex questionIndex=new QuestionIndex();
        questionIndex.setQuestionId(99);
        questionIndex.setQuestionerName("========================");
        System.out.println("-------------------------");
        //questionRepository.save(questionIndex);
        questionRepository.deleteById(99);
    }

}
