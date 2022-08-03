package jungmin.board.service;

import jungmin.board.domain.Content;

import java.util.HashMap;
import java.util.Optional;

public interface ContentService {
    public Optional<Content> contentList() throws Exception;
}
