package testMMMatching;
import java.util.*;

public class testMMMatching 
{
	public static void main(String args[])
	{
		Mentor A = new Mentor("Alex", "40", "chocolatecake@gmail.com", "123-456-7890", "Chandler", "AZ", 
                  "College guidance", "White or European: Includes German, Irish, English, Italian, Polish, and French", "Prefer it, but available to others as needed",
                  "Cisgender Male", "Do not have a preference. Either is fine.", "Web conference (ie. Zoom video conference)", "Mentor (the person providing guidance)",
                  "Student", "Graduate School", "N/A", "N/A", 
                  "Give back to community", "1", "7am to 9am", 
                  "7am to 9am");
		
		Mentor B = new Mentor(
		        "New Mentor",
		        "50",
		        "mentor@asu.edu",
		        "4809990987",
		        "Phoenix",
		        "Arizona",
		        "Career guidance",
		        "South Asian: Includes Indian, Pakistan, Sri Lankan, Bangladesh",
		        "Prefer NOT to be matched within that similarity",
		        "Cisgender Female",
		        "Prefer it, but available to others as needed",
		        "Hybrid (both web and in person)",
		        "Mentor",
		        "Professional",
		        "High School Sophmore",
		        "Teacher",
		        "ASU",
		        "Give back",
		        "6",
		        "9am to 11am",
		        "08012025-08022025");
		
		Mentee C = new Mentee(
		        "Logan Reny",
		        "22",
		        "lreny@asu.edu",
		        "4807660501",
		        "Phoenix",
		        "Arizona",
		        "College guidance",
		        "American Indian or Alaska Native",
		        "Do not have a preference. Either is fine.",
		        "Cisgender Male",
		        "Do not have a preference. Either is fine.",
		        "In person",
		        "Mentee",
		        "Graduate Student",
		        "Do better in school",
		        "Math",
		        "7am to 9am",
		        "20,250,115,202,501,100,000,000,000,000,000");
		
		Mentee E = new Mentee(
		        "Beeb Reny",
		        "22",
		        "lreny@asu.edu",
		        "4807660501",
		        "Phoenix",
		        "Arizona",
		        "College guidance",
		        "White or European: Includes German, Irish, English, Italian, Polish, and French",
		        "Do not have a preference. Either is fine.",
		        "Cisgender Male",
		        "Do not have a preference. Either is fine.",
		        "In person",
		        "Mentee",
		        "Graduate Student",
		        "Do better in school",
		        "Math",
		        "7am to 9am",
		        "20,250,115,202,501,100,000,000,000,000,000");
		
		Mentee D = new Mentee(
		        "test user",
		        "10",
		        "user@asu.edu",
		        "4807660502",
		        "Phoenix",
		        "Arizona",
		        "Career guidance",
		        "American Indian or Alaska Native",
		        "Prefer it, but available to others as needed",
		        "Cisgender Male",
		        "Prefer NOT to be matched within that similarity",
		        "Hybrid",
		        "Mentee",
		        "College Student",
		        "Learn about STEAM",
		        "Dance",
		        "3pmm to 9am",
		        "07252025-07262025");
		
		List<Mentor> a = new ArrayList<Mentor>();
		List<Mentee> b = new ArrayList<Mentee>();
		
		a.add(A);
		a.add(B);
		b.add(C);
		b.add(D);
		b.add(E);
		
		List<Pair> f = new ArrayList<Pair>();
		
		f = matchMM(a, b);
		
		for(Pair q : f)
		{
			q.printPair();
		}
	}

	public static List<Pair> matchMM(List<Mentor> mRs, List<Mentee> mEs)
	{
		//all mentors and mentees
		Mentor[] mentors = new Mentor[mRs.size()];
		mRs.toArray(mentors);
		Mentee[] mentees = new Mentee[mEs.size()];
		mEs.toArray(mentees);
		
		//If mFree[i] is false, then mentee 'i' is free, otherwise paired
		boolean mFree[] = new boolean[mEs.size()];
		Arrays.fill(mFree, false);
		int freeCount = mentees.length;
		
		//there exists a free mentee e who still has a mentor r to propose to
		while (freeCount > 0)//there exists a free mentee who still has a mentor to propose to
		{
			//pick the first free mentee
			//(we could pick any)
			int j;
			for(j = 0; j < mentees.length; j++)
			{
				if(mFree[j] == false)
				{
					break;
				}
			}
			Mentee m = mentees[j];
			//one by one go to all mentors
			//according to m's preferences (ethnicity and gender)
			//m is the picked free mentee
			
			//System.out.println("j " + j);
			for(int i = 0; i < mentors.length && mFree[j] == false; i++)
			{
				Mentor w = mentors[i]; 
				//case 1
				//mentor is free
				
				if(!w.paired)
				{
					//System.out.println("check ethnicity: " + checkEthnicity(w, m));
					//System.out.println("check gender: " + checkGender(w, m));
					//if they match
					/*System.out.println("check age " + checkAge(w,m) + checkMentoringType(w,m));*/
					if(checkEthnicity(w, m) && checkGender(w, m) && checkAge(w, m) && checkMentoringType(w, m))//checkAddressAgeMentor(w, m) && 
					{
						//System.out.println("name?: " + w.name);
						//add them as a pair
						w.pairName = m.name;
						w.paired = true;
						m.pairName = w.name;
						m.paired = true;
						mFree[j] = true;
					}
					freeCount--;
				}

				//case 2
				//mentor is not free
				else
				{
					
					//find current pair
					Mentee m1 = mentees[0];
					for(int k = 0; k < mentees.length; k++)
					{
						if(mentees[k].name.equals(w.pairName))
						{
							m1 = mentees[k];
							break;
						}
					}
					//System.out.println("m1: " + m1);
					//System.out.println("mentor prefers m1 over m?" + mentorPrefersM1OverM(w, m1));
					//if mentor prefers m over current pair m1
					if(!mentorPrefersM1OverM(w, m1))
					{
						//then break the pair between mentor and m1
						//set m1 as free
						m1.pairName = "";
						m1.paired = false;
						//and pair mentor and m
						w.pairName = m.name;
						m.paired = true;
						m.pairName = w.name;

					}

				}
			}
			//no match found
			freeCount--;
		}
		//create pairs based off of matches
		List<Pair> pairs = new ArrayList<Pair>();
		for(Mentor m : mentors)
		{
			if(m.paired)
			{
				for(Mentee n: mentees)
				{
					if(n.name.equals(m.pairName))
					{
						Pair p = new Pair(m.name, m.pairName,m.email, n.email);
						pairs.add(p);
					}
				}
			}
		}
		return pairs;
		

	}
	//only checks whether the original mentee is strictly preferred
	//TODO: Debug
	public static boolean mentorPrefersM1OverM(Mentor mR, Mentee mE1)
	{
		List<String> prefsMentor = findEthPref(mR.ethnicity, mR.ethnicityPreference);
		//List<String> prefsMentee = findEthPref(mE.ethnicity, mE.ethnicityPreference);
		List<String> prefsMentee1 = findEthPref(mE1.ethnicity, mE1.ethnicityPreference);
		//if size is one, they PREFER that ethnicity

		if(prefsMentor.size() == 1)// && prefsMentee1.size() == 1
		{
			/*
			System.out.println("prefsMentee1.get(0)");
			System.out.println("prefsMentor.get(0)");
			if(prefsMentee1.get(0).equals(prefsMentor.get(0))) 
			{
				return true;
			}
			*/
			//System.out.println("Mentee1 size: " + prefsMentee1.size());
			if(prefsMentee1.size() == 1) {}
			if(prefsMentee1.contains(prefsMentor.get(0)))
			{
				return true;
			}
			
		}
		return false;

	}

	//TODO: Implement
	public static boolean mentorPrefersM1OverMGender(Mentor mR, Mentee mE1)
	{
		return true;
	}

	//
	//@param String eth is Mentor.ethnicity, String ethPref is Mentor.ethnicityPreference
	private static List<String> findEthPref(String eth, String ethPref)
	{
		//String ans = "";
		List<String> ans = new ArrayList<String>();
		List<String> eths = new ArrayList<String>();
		eths.add("American Indian or Alaska Native");
		eths.add("Asian: Includes Chinese, Japanese, Filipino, Korean, South Asian, and Vietnamese.");
		eths.add("South Asian: Includes Indian, Pakistan, Sri Lankan, Bangladesh");
		eths.add("Black or African American: Includes Jamaican, Nigerian, Haitian, and Ethiopian");
		eths.add("Hispanic or Latino: Includes Puerto Rican, Mexican, Cuban, Salvadoran, and Colombian");
		eths.add("Middle Eastern or North African: Includes Lebanese, Iranian, Egyptian, Moroccan, Israeli, and Palestinian");
		eths.add("Native Hawaiian or Pacific Islander: Includes Samoan, Buamanian, Chamorro, and Tongan");
		eths.add("White or European: Includes German, Irish, English, Italian, Polish, and French");

		if(ethPref.equals("Prefer ONLY to be matched within that similarity"))
		{
			ans.add(eth);
		}
		else if(ethPref.equals("Prefer it, but available to others as needed"))
		{
			ans.add(eth);

		}
		else if(ethPref.equals("Prefer NOT to be matched within that similarity"))
		{
			//add all strings except the one we dont want, check if string contains correct eth
			for(String word : eths)
			{
				if(word.equals(eth))
				{

				}
				else
				{
					ans.add(word);
				}
			}
			//ans = temp.toString();
		}
		else//do not have a preference
		{
			return eths;

		}

		return ans;
	}

	private static boolean checkDistance(Mentor mR, Mentee mE)
	{
		DistanceCalculator calculator = new DistanceCalculator();
		double distance = 0.0; // Declare the variable outside the try block

		try {
			// Call the getDistance method and assign the result
			distance = calculator.getDistance("Phoenix", "AZ", "Tucson", "AZ");

		} catch (Exception e) {
			e.printStackTrace();
		}
		if(distance <= 60)
			return true;
		return false;
	}
	private static boolean checkAge(Mentor mR, Mentee mE)
	{
		String mentorAge = mR.age;
		String menteeAge = mE.age;
		String[] agesMR = mentorAge.split("-");
		String[] agesME = menteeAge.split("-");
		if(agesMR.length>0 && agesME.length>0)
		{
			if(mR.sessionPreference.equals("Homework Help")
					&& Integer.parseInt(agesMR[0]) >= Integer.parseInt(agesME[0]))
			{
				return true;
			}

			if(Integer.parseInt(agesMR[0]) > Integer.parseInt(agesME[0]))
			{
				return true;
			}

		}
		else {
			return true;
		}
		return false;
	}
	
	
	private static boolean checkMentoringType(Mentor mR, Mentee mE)
	{
		//mentoring type
		String sp = mR.sessionPreference;
		String[] categories = sp.split(",");
		
		String[] categoriesE = mE.sessionPreference.split(",");
		
		for(String category : categories)//mentoringType i : mentee
		{
			for(String cat : categoriesE)
			{
				if(cat.equals(category))//mentor.mentoringType.contains(mentee.mentoringType[i])
				{
					return true;
				}
			}


		}
		
		return false;
		
	}
	

	private static boolean checkEthnicity(Mentor mR, Mentee mE)
	{
		//start by adding possible ethnicity matches
		boolean ans = false;
		
		//for later comparisons
		List<String> mReths = new ArrayList<String>();
		List<String> mEeths = new ArrayList<String>();
		List<String> eths = new ArrayList<String>();
		
		//options for comparison
		eths.add("American Indian or Alaska Native");
		eths.add("Asian: Includes Chinese, Japanese, Filipino, Korean, South Asian, and Vietnamese.");
		eths.add("South Asian: Includes Indian, Pakistan, Sri Lankan, Bangladesh");
		eths.add("Black or African American: Includes Jamaican, Nigerian, Haitian, and Ethiopian");
		eths.add("Hispanic or Latino: Includes Puerto Rican, Mexican, Cuban, Salvadoran, and Colombian");
		eths.add("Middle Eastern or North African: Includes Lebanese, Iranian, Egyptian, Moroccan, Israeli, and Palestinian");
		eths.add("Native Hawaiian or Pacific Islander: Includes Samoan, Buamanian, Chamorro, and Tongan");
		eths.add("White or European: Includes German, Irish, English, Italian, Polish, and French");
		
		String mRstr = mR.ethnicityPreference;
		String mEstr = mE.ethnicityPreference;

		//case 1
		//add pref ethn
		if(mRstr.equals("Prefer ONLY to be matched within that similarity"))
		{
			//if(eths.contains(eth))
			//{
			mReths.add(mR.ethnicity);
			//}
		}

		//case 2
		//add pref ethn
		else if(mRstr.equals("Prefer it, but available to others as needed"))
		{
			for(String word : eths)
			{
				mReths.add(word);
			}

		}

		//case 3
		//add all but mR's ethn
		else if(mRstr.equals("Prefer NOT to be matched within that similarity"))
		{
			for(String word : eths)
			{
				if(mR.ethnicity.equals(word))
				{

				}
				else
				{
					mReths.add(mR.ethnicity);
				}
			}

		}
		
		//no pref - add all words
		else
		{
			for(String word : eths)
			{
				mReths.add(word);
			}
		}
		
		//add pref ethn
		if(mEstr.equals("Prefer ONLY to be matched within that similarity"))
		{
			mEeths.add(mE.ethnicity);
		}

		//add pref ethn
		else if(mEstr.equals("Prefer it, but available to others as needed"))
		{
			
			for(String word : eths)
			{
				//System.out.println("word: " + word);
				mEeths.add(word);
			}

		}

		//add all but mE's ethn
		else if(mEstr.equals("Prefer NOT to be matched within that similarity"))
		{
			for(String word : eths)
			{
				if(mE.ethnicity.equals(word))
				{

				}
				else
				{
					mEeths.add(mE.ethnicity);
				}
			}
		}
		//no pref - add all words
		else
		{
			for(String word : eths)
			{
				mEeths.add(word);
			}
		}

		//compare possible ethnicity matches
		for(String ethn : mEeths)
		{

			if(mR.ethnicity.equals(ethn))
			{
				ans = true;
			}
		}
		return ans;
	}

	private static boolean checkGender(Mentor mR, Mentee mE)
	{
		//start by adding possible gender matches
		boolean ans = false;
		
		//for later comparisons
		List<String> mReths = new ArrayList<String>();
		List<String> mEeths = new ArrayList<String>();
		List<String> eths = new ArrayList<String>();
		//check options
		eths.add("Cisgender Male");
		eths.add("Cisgender Female");
		eths.add("Transgender Male");
		eths.add("Transgender Female");
		eths.add("Prefer not to disclose");

		String mRstr = mR.genderPreference;
		String mEstr = mE.genderPreference;

		//case 1
		//add the preferred gender
		if(mRstr.equals("Prefer ONLY to be matched within that similarity"))
		{
			//if(eths.contains(eth))
			//{
			mReths.add(mR.gender);
			//}
		}

		//case 2
		//add the preferred gender
		else if(mRstr.equals("Prefer it, but available to others as needed"))
		{
			for(String word : eths)
			{
				mReths.add(word);
			}

		}

		//case 3
		//add all genders but mR's
		else if(mRstr.equals("Prefer NOT to be matched within that similarity"))
		{
			for(String word : eths)
			{
				if(mR.ethnicity.equals(word))
				{

				}
				else
				{
					mReths.add(mR.gender);
				}
			}

		}
		//case 4 
		//no preference - add all words
		else
		{
			for(String word : eths)
			{
				mReths.add(word);
			}
		}
		
		//add the preferred gender
		if(mEstr.equals("Prefer ONLY to be matched within that similarity"))
		{
			mEeths.add(mE.gender);
		}

		//add the preferred gender
		else if(mEstr.equals("Prefer it, but available to others as needed"))
		{
			for(String word : eths)
			{
				mEeths.add(word);
			}

		}
		
		//add all but the mE's gender
		else if(mEstr.equals("Prefer NOT to be matched within that similarity"))
		{
			for(String word : eths)
			{
				if(mR.gender.equals(word))
				{

				}
				else
				{
					mEeths.add(mR.gender);
				}
			}
		}
		//no pref - add all words
		else
		{
			for(String word : eths)
			{
				mEeths.add(word);
			}
		}

		//compare possible gender matches
		for(String ethn : mEeths)
		{
			if(mR.gender.equals(ethn))
			{
				ans = true;
			}
		}
		return ans;
	}
	

}
