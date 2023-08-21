package com.example.ProjectGoods;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class ProjectGoodsApplicationTests {
	Calculator underTest = new Calculator();
	@Test
	void itShouldAddNumbers() {
		//given
		int number1 = 20;
		int number2 = 40;
		//when
		int result = underTest.add(number1, number2);
		//then
		assertThat(result).isEqualTo(61);
	}
	class Calculator{
		int add(int a, int b){
			return a+b;
		}
	}

}
