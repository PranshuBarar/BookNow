package com.example.online_movie_ticketing_application;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//@SpringBootTest
class BookMyShowApplicationTests {

	private Calculator c = new Calculator();

	@Test
	@Disabled
	void contextLoads() {
	}

	@Test
	@Disabled
	void testSum(){

		//expected
		int expectedResult = 17;

		//actual
		int actualResult = c.doSum(12,3,2);

		assertThat(actualResult).isEqualTo(expectedResult);
	}

	@Test
	void testProduct(){
		//expectedResult
		int expectedResult = 6;

		//actual
		int actualResult = c.doMult(3,2);

		assertThat(actualResult).isEqualTo(expectedResult);
	}

	@Test
	void testCompareNums(){
		boolean actualResult = c.compareTwoNums(3,3);
		assertThat(actualResult).isTrue();
	}

}
