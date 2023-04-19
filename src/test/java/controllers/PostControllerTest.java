package controllers;

import static org.junit.Assert.assertEquals;
import ninja.NinjaTest;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import models.Post;
import ninja.Result;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import dao.PostDao;
import dao.UserDao;

public class PostControllerTest {

	
	@Mock
	private PostDao postdao;
	
	
	@InjectMocks
	private PostController postController;
	
	
	@Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testGetAllPosts() {
		
//		List<Post> postList = new ArrayList<>();
//		Post post = new Post();
//		post.content="beautiful image";
//		post.image="https://images.unsplash.com/photo-1575936123452-b67c3203c357?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8aW1hZ2V8ZW58MHx8MHx8&w=1000&q=80";
//		post.id=2l;
//		post.subtitle="Farrukhabad";
//		
//		postList.add(post);
//		when(postdao.getAllPosts()).thenReturn( postList);
		Result result = postController.getAllPost();
		verify(postdao).getAllPosts();
		
//		String response = ninjaTestBrowser.makeJsonRequest(getServerAddress()+"/post");
		assertEquals(200,result.getStatusCode());
//		Result data = postController.getAllPost();
//		assertEquals(postList ,postList.get(0));
	}
	
	@Test
	public void testGetPost() {
		Result result = postController.getPost(2l);
		verify(postdao).getPost(2l);
		assertEquals(200,result.getStatusCode());
	}
	
//	@Test
//	public void testUpdatePost() {
//		Post post = new Post();
//		post.content="beautiful image";
//		post.image="https://images.unsplash.com/photo-1575936123452-b67c3203c357?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8aW1hZ2V8ZW58MHx8MHx8&w=1000&q=80";
//		post.id=1l;
//		post.subtitle="Farrukhabad";
//		Result result = postController.updatePost(1l, post);
//		verify(postdao).updatePost(post,1l);
//		assertEquals(200, result.getStatusCode());
//	}
	
	@Test
	public void testDeletePost(){
		Result result = postController.deletePost(1l);
		verify(postdao).deletePost(1l);
		assertEquals(200, result.getStatusCode());
	}
	
	@Test
	public void testGetMyPost() {
		Result result = postController.getMyPost("bob@gmail.com");
		verify(postdao).getUserPosts("bob@gmail.com");
		assertEquals(200, result.getStatusCode());
	}
}
