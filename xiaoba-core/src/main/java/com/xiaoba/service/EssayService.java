package com.xiaoba.service;

import com.xiaoba.entity.Essay;

import java.util.List;

public interface EssayService {
    List<Essay> getEssaies(String author);

    Essay getEssay( Integer id);
}
