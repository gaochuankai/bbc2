package com.bbc.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

public class Question {

	/**
	 * ��ҵ���ŵĽ������������ɡ� ����(I)���ڻ����10��Ԫʱ���������10%�� �������10��Ԫ������20��Ԫʱ������10��Ԫ�Ĳ��ְ�10%��ɣ�
	 * ����10��Ԫ�Ĳ��֣������7.5%�� 20��40��֮��ʱ������20��Ԫ�Ĳ��֣������5%��
	 * 40��60��֮��ʱ������40��Ԫ�Ĳ��֣������3%�� 60��100��֮��ʱ������60��Ԫ�Ĳ��֣������1.5%��
	 * ����100��Ԫʱ������100��Ԫ�Ĳ��ְ�1%��ɣ��Ӽ������뵱������I����Ӧ���Ž���������
	 */
	@Test
	public void test1() {
		Long[] arr = { 100l, 60l, 40l, 20l, 10l, 0l };
		for (int i = 0; i < arr.length; i++) {
			arr[i] = arr[i] * 10000;
		}
		System.out.println(Arrays.toString(arr));
		Double[] arr2 = { 0.01, 0.015, 0.03, 0.05, 0.075, 0.1 };

		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			Long menoy = scanner.nextLong();
//			System.out.println(Long.MAX_VALUE);
			if (menoy < 0) {
				System.out.println("����С����");
			} else if (menoy > Long.MAX_VALUE) {
				System.out.println(menoy);
			} else {
				Double count = 0.00;
				for (int i = 0; i < 6; i++) {
					if (menoy > (arr[i])) {
						count += (menoy - arr[i]) * arr2[i];
						menoy -= arr[i];
					}
				}
				System.out.println(count);
			}
		}
		scanner.close();
	}

	@Test
	public void test2() {

		int i = test3();

		System.out.println("shuchu");
		System.out.println(i);
		Map<String, Object> map = new HashMap<String, Object>();
		ConcurrentHashMap<String, Object> concurrentHashMap = new ConcurrentHashMap<String, Object>();
	}

	public int test3() {
		try {
			int i = 10 / 0;
			return 33;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
