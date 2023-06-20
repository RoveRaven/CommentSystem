package com.github.roveraven.service;

import com.github.roveraven.repository.CommentRepository;
import com.github.roveraven.repository.entity.Comment;
import com.github.roveraven.repository.entity.User;
import com.github.roveraven.repository.entity.dto.CommentDto;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;
    private final UserService userService;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, UserService userService)
    {
        this.commentRepository = commentRepository;
        this.userService = userService;
    }

    @Override
    public Comment save(Comment comment)
    {
    	log.info("Start SAVE command in CommentService, CommentInfo: \n" + comment.toString());
    	Instant startTimeInstant = Instant.now();
    	
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User userFromDB = userService.findByUsername(userDetails.getUsername());
        comment.setCommentTime(ZonedDateTime.now(ZoneId.systemDefault()));
        comment.setLevel(0);
        if(comment.getParentId()!=null) 
        {
        	comment.setParentComment(commentRepository.findById(comment.getParentId()).get());
        	comment.setLevel(comment.getParentComment().getLevel()+1);
        }
        
        comment.setUser(userFromDB);
        comment.setImage(null);
        Comment savedComment = commentRepository.save(comment);
        Instant endTimeInstant = Instant.now();
        log.info(String.format("SAVE command executed. Time of execution: %d milliseconds", endTimeInstant.toEpochMilli()-startTimeInstant.toEpochMilli()));
        return savedComment;
    }

    @Override
    public Optional<Comment> findComment(Long CommentId) {
        return Optional.empty();
    }

    @Override
    public List<Comment> findByUser(Long userId) {
        return null;
    }

    @Override
    public List<Comment> findAll() {
    	log.info("Start FIND_ALL command in CommentService");
    	Instant startTimeInstant = Instant.now();
    	List<Comment> resultComments = commentRepository.findAll();
    	Instant endTimeInstant = Instant.now();
        log.info(String.format("FIND_ALL command executed. Time of execution: %d milliseconds", endTimeInstant.toEpochMilli()-startTimeInstant.toEpochMilli()));
        return resultComments;
    }
    
    public List<CommentDto> findAllDto(){
    	log.info("Start FIND_ALL_DTO command in CommentService");
    	Instant startTimeInstant = Instant.now();
    	List<CommentDto> resultComments = commentRepository.findAll()
    			.stream()
    			.map(c -> convertToDto(c))
    			.collect(Collectors.toList());
    	Instant endTimeInstant = Instant.now();
        log.info(String.format("FIND_ALL_DTO command executed. Time of execution: %d milliseconds", endTimeInstant.toEpochMilli()-startTimeInstant.toEpochMilli()));
       /* Comparator<CommentDto> comparator = Comparator.comparing(CommentDto::getTopId)
        		.reversed()
        		.thenComparing(CommentDto::getParentCommentId)
        		.thenComparing(CommentDto::getId);
        Collections.sort(resultComments, comparator); */
        return resultComments;
    }
    
    public List<CommentDto> getSortedDtoList() {
		List<Comment> rootComments = commentRepository.findAll().stream()
				.filter(c -> c.getParentComment()==null)
				//.map(c -> convertToDto(c))
				.sorted((c1, c2) -> (int)(c2.getId()-c1.getId()))
				.collect(Collectors.toList());
		List<Comment> resultList = new ArrayList<>();
		for (Comment comment : rootComments) {
			resultList.add(comment);
			findNestedComments(comment.getSubComments(), resultList);
		}
		//findNestedComments(rootComments, resultList);
		List<CommentDto> result = resultList.stream()
				.map(c -> convertToDto(c))
				.collect(Collectors.toList());
				
		return result;
	}
	
	private void findNestedComments(List<Comment> comments, List<Comment> list) {
		for(Comment c : comments) {
			if(c.getSubComments()==null) {
				list.add(c);
			}
			else {
				list.add(c);
				findNestedComments(c.getSubComments(), list);
				
			}
		}
	}
    
    private CommentDto convertToDto(Comment comment) {
    	CommentDto dto = new CommentDto();
    	dto.setId(comment.getId());
    	dto.setUserId(comment.getUser().getId());
    	dto.setUsername(comment.getUser().getUsername());
    	dto.setText(comment.getText());
    	dto.setParentCommentId(comment.getParentId()==null? 0 : comment.getParentId());
    	dto.setLevel(comment.getLevel());
    	dto.setTime(comment.getCommentTime());
    	return dto;
    }
}
