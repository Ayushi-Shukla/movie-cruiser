package com.stackroute.moviecruiserserver.repositoryTest;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.*;

import javax.transaction.Transactional;

import com.stackroute.moviecruiserserver.MoviecruiserserverApplication;
import com.stackroute.moviecruiserserver.domain.*;
import com.stackroute.moviecruiserserver.repository.*;

@ContextConfiguration(classes=MoviecruiserserverApplication.class)
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Transactional
public class RepositoryTest {
	

	@Autowired
	private transient MovieRepository repo;

	public void setRepo(MovieRepository repo) {
		this.repo = repo;
	}
	
	@After
	public void tearDown() {
		repo.deleteAllInBatch();
	  }
	
	@Test
	public void testSaveMovie() throws Exception {
		final Movie movie = repo.save(new Movie(1234,1,"POC","good movie","www.abc.com","2015-03-23",45.5,112,"John123"));
		assertThat(movie.getTitle()).isEqualTo("POC");
	}
	
	@Test
	public void testUpdateMovie() throws Exception {
		
		final Movie movie = repo.save(new Movie(1234,1,"POC","good movie","www.abc.com","2015-03-23",45.5,112,"John123"));
		assertEquals("POC", movie.getTitle());
		movie.setComments("Hi");
		Movie tempMovie = repo.save(movie);
		assertEquals("Hi", tempMovie.getComments());
		repo.delete(tempMovie);
	}
	
	@Test
	public void testDeleteMovie() throws Exception {
		final Movie movie = 	repo.save(new Movie(1234,1,"POC","good movie","www.abc.com","2015-03-23",45.5,112,"John123"));
		assertEquals("POC", movie.getTitle());
		repo.delete(movie);
		assertEquals(Optional.empty(), repo.findById(1));
	}

	@Test
	public void testGetMovie() throws Exception {
		final Movie movie = repo.save(new Movie(1234,1,"POC","good movie","www.abc.com","2015-03-23",45.5,112,"John123"));
		assertEquals("POC", movie.getTitle());
	}

	@Test
	public void testGetAllMovies() throws Exception {
		repo.save(new Movie(1234,1,"POC","good movie","www.abc.com","2015-03-23",45.5,112,"John123"));
		repo.save(new Movie(1234,1,"POC-1","good movie","www.abc.com","2015-03-23",45.5,112,"John123"));
		repo.save(new Movie(1234,1,"POC-2","good movie","www.abc.com","2015-03-23",45.5,112,"John123"));
		repo.save(new Movie(1234,1,"POC-3","good movie","www.abc.com","2015-03-23",45.5,112,"John123"));
		List<Movie> myMovies = repo.findByUserId("John123");
		assertEquals(myMovies.size(),4);
	}
}

