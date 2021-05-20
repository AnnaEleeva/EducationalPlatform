package com.project.EducationalPlatform.quest;

import com.project.EducationalPlatform.controller.SubjectsController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class QuestMaker {
	public static ArrayList<ArrayList<Question>> questions = new ArrayList<ArrayList<Question>>() {
	};
	public static String pathToTxtFiles = "src/main/resources/txtFiles/tests";

	public static void fw() {

		// here we decide how many levels skill will have. We just see count of files in
		// the directory
		 //String parentFolderPath = "C:\\Users\\Anna\\IdeaProjects\\gameJavaFX1(2)\\src\\txtFiles\\ru";
		String parentFolderPath = pathToTxtFiles + "/ru";
		File parent = new File(parentFolderPath);
		int countFiles = parent.listFiles().length; // levels = count files

		for (int level = 1; level <= countFiles; level++) {
			ArrayList<String> listWordsRu = new ArrayList<String>();
			ArrayList<String> listWordsDe = new ArrayList<String>();

			// here we take files and fill listWords. for one level
			try {
				 File file = new
				 File(pathToTxtFiles + "\\ru\\" +
				 level + "ru.txt");
			/*	File file = new File(
						"C:\\My_Files\\Spring_work\\col_version\\src\\main\\resources\\txtFiles\\tests\\ru\\"
								+ level + "ru.txt");*/
				FileReader fr = new FileReader(file);
				BufferedReader reader = new BufferedReader(fr);
				String line = reader.readLine();
				while (line != null) {

					listWordsRu.add(line); // ru0 ru1
					line = reader.readLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				 File file2 = new
				 File(pathToTxtFiles + "\\" +
				 SubjectsController.subjectEnum.getTitle() + "\\" + level +
				 SubjectsController.subjectEnum.getTitle() + ".txt");
				/*File file2 = new File(
						"C:\\My_Files\\Spring_work\\col_version\\src\\main\\resources\\txtFiles\\tests\\"
								+ SubjectsController.subjectEnum.getTitle() + "\\" + level
								+ SubjectsController.subjectEnum.getTitle() + ".txt");*/
				FileReader fr2 = new FileReader(file2);
				BufferedReader reader2 = new BufferedReader(fr2);
				String line2 = reader2.readLine();
				while (line2 != null) {

					listWordsDe.add(line2); // ru0 ru1
					line2 = reader2.readLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			// here we create an object Questions. We take lists with words and put it in
			// new object
			questions.add(level-1, new ArrayList<Question>() {
			});
			for (int j = 0; j < listWordsRu.size(); j++) {
				int firstRandomIndex = getRandomFromList(listWordsDe, j);
				int secondRandomIndex = getRandomFromList(listWordsDe, j, firstRandomIndex);

				questions.get(level-1).add(j,
						new Question(
								listWordsRu.get(j), new String[] { listWordsDe.get(j),
										listWordsDe.get(firstRandomIndex), listWordsDe.get(secondRandomIndex) },
								listWordsDe.get(j)));
				// questions[i][j]=new Questions(ru1.get(j),new String[]{de1.get(j),
				// de1.get(firstRandomIndex),de1.get(secondRandomIndex)},j);
			}
		}

	}

	private static int getRandomFromList(ArrayList<String> arrayDe, int... v) {
		Random rand = new Random();
		ArrayList<Integer> givenList = new ArrayList<>();
		for (int i = 0; i < arrayDe.size(); i++) {
			givenList.add(i);
		}
		System.out.println("givenList " + givenList.size());

		if (v.length == 1) {
			givenList.remove(v[0]);
		}
		if (v.length == 2) {
			if (v[0] > v[1]) {
				givenList.remove(v[0]);
				givenList.remove(v[1]);
			} else {
				givenList.remove(v[1]);
				givenList.remove(v[0]);
			}
		}
		System.out.println("givenList2 " + givenList.size());

		int temp = rand.nextInt(givenList.size());
		int randomIndex = givenList.get(temp);

		System.out.println("randomIndex " + randomIndex);
		return randomIndex;
	}
}
