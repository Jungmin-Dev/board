package jungmin.board.service;

import jungmin.board.domain.Content;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface ContentService {
    public List<Content> contentList() throws Exception;
}
