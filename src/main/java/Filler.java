import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import org.postgresql.util.PSQLException;


public class Filler {

	public Connection con = null;

	public Filler(Connection con) {
		this.con = con;
	}

	/**
	 * Global annotation:
	 * String thisLine = br.readLine(); to skip the head of each read file.
	 */

	public void addPlaces () {
		try {
			BufferedReader br = new BufferedReader(new FileReader("./data/place_0_0.csv"));
			String thisLine = br.readLine();
			Statement stmt = this.con.createStatement();

			ArrayList<String[]> countries = new ArrayList<>();
			ArrayList<String[]> cities = new ArrayList<>();

			while ((thisLine = br.readLine()) != null) {
				thisLine = thisLine.replace("'", "''");
				String[] strings = thisLine.split("\\|");

				String sql = "INSERT INTO place VALUES (" + strings[0] + ",'" + strings[1] + "');";

				switch (strings[3]) {
					case "continent": {
						sql += "INSERT INTO continent VALUES (" + strings[0] + "," + strings[0] + ");";
						break;
					}
					case "country": {
						countries.add(strings);
						break;
					}
					case "city": {
						cities.add(strings);
						break;
					}
					default:
						System.out.println("undefinedCase@addPlaces: " + thisLine);
				}

				try {
					stmt.executeUpdate(sql);
				} catch (PSQLException x) {
					System.out.println("Error@addPlaces: " + x.getMessage() + "\n" + sql);
				}
			}

			// add countries
			for (String[] strings : countries) {
				String sql = "INSERT INTO country VALUES (" + strings[0] + "," + strings[0] + "," + strings[4] + ");";
				try {
					stmt.executeUpdate(sql);
				} catch (PSQLException x) {
					System.out.println("Error@addPlaces@countries: " + x.getMessage() + "\n" + sql);
				}
			}

			// add cities
			for (String[] strings : cities) {
				String sql = "INSERT INTO city VALUES (" + strings[0] + "," + strings[0] + "," + strings[4] + ");";
				try {
					stmt.executeUpdate(sql);
				} catch (PSQLException x) {
					System.out.println("Error@addPlaces@cities: " + x.getMessage() + "\n" + sql);
				}
			}

			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addOrganisations() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("./data/organisation_0_0.csv"));
			String thisLine = br.readLine();
			Statement stmt = this.con.createStatement();

			while ((thisLine = br.readLine()) != null) {
				thisLine = thisLine.replace("'", "''");
				String[] strings = thisLine.split("\\|");

				String sql = "INSERT INTO organisation VALUES (" + strings[0] + ",'" + strings[2] + "');";

				switch (strings[1]) {
					case "company": {
						sql += "INSERT INTO company VALUES (" + strings[0] + "," + strings[0] + "," + strings[4] + ");";
						break;
					}
					case "university": {
						sql += "INSERT INTO university VALUES (" + strings[0] + "," + strings[0] + "," + strings[4] + ");";
					}
				}

				try {
					stmt.executeUpdate(sql);
				} catch (PSQLException x) {
					System.out.println("Error@addOrganisation: " + x.getMessage() + "\n" + sql);
				}
			}
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addTag() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("./data/tag_0_0.csv"));
			String thisLine = br.readLine();
			Statement stmt = this.con.createStatement();

			while ((thisLine = br.readLine()) != null) {
				String[] strings = thisLine.split("\\|");

				String sql = "INSERT INTO tag VALUES (" + strings[0] + ",'" + strings[1] + "');";
				try {
					stmt.executeUpdate(sql);
				} catch (PSQLException x) {
					System.out.println("Error@addTag: " + x.getMessage() + "\n" + sql);
				}
			}
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addTagClass() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("./data/tagclass_0_0.csv"));
			String thisLine = br.readLine(); // skip the head
			Statement stmt = this.con.createStatement();

			while ((thisLine = br.readLine()) != null) {
				String[] strings = thisLine.split("\\|");
				String sql = "INSERT INTO tag_class VALUES ("+strings[0]+",'"+strings[1]+"');";
				try {
					stmt.executeUpdate(sql);
				} catch(PSQLException x) {
					System.out.println("Error@addTagClass: " + x.getMessage() + "\n" + sql);
				}
			}
			br.close();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void addTagClassIsSubclassOf() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("./data/tagclass_isSubclassOf_tagclass_0_0.csv"));
			String thisLine = br.readLine();
			Statement stmt = this.con.createStatement();

			while ((thisLine = br.readLine()) != null) {
				String[] strings = thisLine.split("\\|");
				String sql = "INSERT INTO is_subclass_of VALUES ("+strings[0]+",'"+strings[1]+"');";
				try {
					stmt.executeUpdate(sql);
				} catch(PSQLException x) {
					System.out.println("Error@addTagClassIsSubclassOf: " + x.getMessage() + "\n" + sql);
				}
			}
			br.close();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void addHasType() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("./data/tag_hasType_tagclass_0_0.csv"));
			String thisLine = br.readLine();
			Statement stmt = this.con.createStatement();

			while ((thisLine = br.readLine()) != null) {
				String[] strings = thisLine.split("\\|");

				String sql = "INSERT INTO has_type VALUES (" + strings[0] + "," + strings[1] + ");";
				try {
					stmt.executeUpdate(sql);
				} catch (PSQLException x) {
					System.out.println("Error@addHasType: " + x.getMessage() + "\n" + sql);
				}
			}
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addPerson() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("./data/person_0_0.csv"));
			String thisLine = br.readLine();
			Statement stmt = this.con.createStatement();

			while ((thisLine = br.readLine()) != null) {
				String[] strings = thisLine.split("\\|");

				String sql = "INSERT INTO person VALUES ('"
						+ strings[0] + "','" + strings[8] + "'," + "TO_TIMESTAMP('" + strings[5] + "','YYYY-MM-DDTHH24:MI:SS.MS+SSSS')" + ",'"
						+ strings[1] + "','" + strings[2] + "','" + strings[3] + "'," + "TO_DATE('" + strings[4] + "',' YYYY-MM-DD')" + ",'"
						+ strings[7] + "','" + strings[6] + "');";
				try {
					stmt.executeUpdate(sql);
				} catch (PSQLException x) {
					System.out.println("Error@addPerson: " + x.getMessage() + "\n" + sql);
				}

			}
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addSpeaks() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("./data/person_speaks_language_0_0.csv"));
			String thisLine = br.readLine();
			Statement stmt = this.con.createStatement();
			while ((thisLine = br.readLine()) != null) {
				String[] strings = thisLine.split("\\|");

				String sql = "INSERT INTO person_speaks (language_person_id, language) VALUES (" + strings[0] + ",'" + strings[1] + "');";

				try {
					stmt.executeUpdate(sql);
				} catch (PSQLException x) {
					System.out.println("Error@addEmail: " + x.getMessage() + "\n" + sql);
				}
			}
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void addEmail() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("./data/person_email_emailaddress_0_0.csv"));
			String thisLine = br.readLine();
			Statement stmt = this.con.createStatement();

			while ((thisLine = br.readLine()) != null) {
				String[] strings = thisLine.split("\\|");

				String sql = "INSERT INTO person_email (email_person_id, email) VALUES (" + strings[0] + ",'"
						+ strings[1] + "');";

				try {
					stmt.executeUpdate(sql);
				} catch (PSQLException x) {
					System.out.println("Error@addEmail: " + x.getMessage() + "\n" + sql);
				}
			}
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addKnows() {
		try {
	         BufferedReader br = new BufferedReader(new FileReader("./data/person_knows_person_0_0.csv"));
	         String thisLine = br.readLine();
	         Statement stmt = this.con.createStatement();

	         while ((thisLine = br.readLine()) != null) {
	        	String[] strings=thisLine.split("\\|");

	        	String sql = "INSERT INTO knows VALUES ("+strings[0]+","+strings[1]+","
						   + "TO_TIMESTAMP('"+strings[2]+"','YYYY-MM-DDTHH24:MI:SS.MS+SSSS'));";
	        	try { 
	        		stmt.executeUpdate(sql);
	        	} catch(PSQLException x) {
	        		System.out.println("Error@addKnows: " + x.getMessage() + "\n" + sql);
	        	}
	         } 
	         br.close();

	      } catch(Exception e) {
	         e.printStackTrace();
	      }
	}

	public void addStudyAt() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("./data/person_studyAt_organisation_0_0.csv"));
			String thisLine = br.readLine();
			Statement stmt = this.con.createStatement();
			while ((thisLine = br.readLine()) != null) {
				String[] strings = thisLine.split("\\|");

				String sql = "INSERT INTO study_at VALUES (" + strings[0] + "," + strings[1] + "," + strings[2] + ");";
				try {
					stmt.executeUpdate(sql);
				} catch (PSQLException x) {
					System.out.println("Error@addStudyAt: " + x.getMessage() + "\n" + sql);
				}
			}
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addWorkAt() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("./data/person_workAt_organisation_0_0.csv"));
			String thisLine = br.readLine();
			Statement stmt = this.con.createStatement();
			while ((thisLine = br.readLine()) != null) {
				String[] strings = thisLine.split("\\|");

				String sql = "INSERT INTO work_at VALUES (" + strings[0] + "," + strings[1] + "," + strings[2] + ");";
				try {
					stmt.executeUpdate(sql);
				} catch (PSQLException x) {
					System.out.println("Error@AddWorkAt: " + x.getMessage() + "\n" + sql);
				}
			}
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addHasInterest() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("./data/person_hasInterest_tag_0_0.csv"));
			String thisLine = br.readLine();
			Statement stmt = this.con.createStatement();

			while ((thisLine = br.readLine()) != null) {
				thisLine = thisLine.replace("'", "''");
				String[] strings = thisLine.split("\\|");

				String sql = "INSERT INTO has_interest VALUES (" + strings[0] + "," + strings[1] + ");";
				try {
					stmt.executeUpdate(sql);
				} catch (PSQLException x) {
					System.out.println("Error@addHasInterest: " + x.getMessage() + "\n" + sql);
				}
			}
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addForum() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("./data/forum_0_0.csv"));
			String thisLine = br.readLine();
			Statement stmt = this.con.createStatement();

			while ((thisLine = br.readLine()) != null) {
				thisLine = thisLine.replace("'", "''");
				String[] strings = thisLine.split("\\|");

				String sql = "INSERT INTO forum VALUES (" + strings[0] + "," + strings[3] + ",'"
						+ strings[1] + "', TO_TIMESTAMP('" + strings[2] + "','YYYY-MM-DDTHH24:MI:SS.MS+SSSS'));";
				try {
					stmt.executeUpdate(sql);
				} catch (PSQLException x) {
					System.out.println("Error@addForum: " + x.getMessage() + "\n" + sql);
				}
			}
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addHasMember() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("./data/forum_hasMember_person_0_0.csv"));
			String thisLine = br.readLine();
			Statement stmt = this.con.createStatement();

			while ((thisLine = br.readLine()) != null) {
				thisLine = thisLine.replace("'", "''");
				String[] strings = thisLine.split("\\|");

				String sql = "INSERT INTO has_member VALUES (" + strings[0] + "," + strings[1]
						+ ", TO_DATE('" + strings[2] + "','YYYY-MM-DDTHH24:MI:SS.MS+SSSS'));";
				try {
					stmt.executeUpdate(sql);
				} catch (PSQLException x) {
					System.out.println("Error@addHasMember: " + x.getMessage() + "\n" + sql);
				}
			}
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addForumHasTag() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("./data/forum_hasTag_tag_0_0.csv"));
			String thisLine = br.readLine();
			Statement stmt = this.con.createStatement();

			while ((thisLine = br.readLine()) != null) {
				thisLine = thisLine.replace("'", "''");
				String[] strings = thisLine.split("\\|");

				String sql = "INSERT INTO forum_has_tag VALUES (" + strings[0] + "," + strings[1] + ");";
				try {
					stmt.executeUpdate(sql);
				} catch (PSQLException x) {
					System.out.println("Error@addForumHasTag: " + x.getMessage() + "\n" + sql);
				}
			}
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addMessagePost() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("./data/post_0_0.csv"));
			String thisLine = br.readLine();
			Statement stmt = this.con.createStatement();

			while ((thisLine = br.readLine()) != null) {
				thisLine = thisLine.replace("'", "''");
				String[] strings = thisLine.split("\\|");

				String sql = "INSERT INTO message VALUES (" + strings[0] + "," + strings[8] + ","
						+ strings[10] + ", TO_DATE('" + strings[2] + "','YYYY-MM-DDTHH24:MI:SS.MS+SSSS'),"
						+ "'" + strings[4] + "','" + strings[3] + "','" + strings[6] + "'," + strings[7] + ");";

				sql += "INSERT INTO post VALUES (" + strings[0] + "," + strings[0] + "," + strings[9] + ",'" + strings[5] + "','" + strings[1] + "');";
				try {
					stmt.executeUpdate(sql);
				} catch (PSQLException x) {
					System.out.println("Error@addMessagePost: " + x.getMessage() + "\n" + sql);
				}
			}
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addMessageComment() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("./data/comment_0_0.csv"));
			String thisLine = br.readLine();
			Statement stmt = this.con.createStatement();

			while ((thisLine = br.readLine()) != null) {
				thisLine = thisLine.replace("'", "''");
				String[] strings = thisLine.split("\\|");

				String sql = "INSERT INTO message VALUES (" + strings[0] + "," + strings[6] + ","
						+ strings[7] + ", TO_DATE('" + strings[1] + "','YYYY-MM-DDTHH24:MI:SS.MS+SSSS'),"
						+ "'" + strings[3] + "','" + strings[2] + "','" + strings[4] + "'," + strings[5] + ");";

				String messageID = (!strings[8].equals("")) ? strings[8] : strings[9];
				sql += "INSERT INTO comment VALUES (" + strings[0] + "," + strings[0] + "," + messageID + ");";
				try {
					stmt.executeUpdate(sql);
				} catch (PSQLException x) {
					System.out.println("Error@addMessageComment: " + x.getMessage() + "\n" + sql);
				}
			}
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addHasTagPost() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("./data/post_hasTag_tag_0_0.csv"));
			String thisLine = br.readLine();
			Statement stmt = this.con.createStatement();

			while ((thisLine = br.readLine()) != null) {
				thisLine = thisLine.replace("'", "''");
				String[] strings = thisLine.split("\\|");

				String sql = "INSERT INTO message_has_tag VALUES (" + strings[0] + "," + strings[1] + ");";
				try {
					stmt.executeUpdate(sql);
				} catch (PSQLException x) {
					System.out.println("Error@addHasTagPost: " + x.getMessage() + "\n" + sql);
				}
			}
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addHasTagComment() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("./data/comment_hasTag_tag_0_0.csv"));
			String thisLine = br.readLine();
			Statement stmt = this.con.createStatement();

			while ((thisLine = br.readLine()) != null) {
				thisLine = thisLine.replace("'", "''");
				String[] strings = thisLine.split("\\|");

				String sql = "INSERT INTO message_has_tag VALUES (" + strings[0] + "," + strings[1] + ");";
				try {
					stmt.executeUpdate(sql);
				} catch (PSQLException x) {
					System.out.println("Error@addHasTagComment: " + x.getMessage() + "\n" + sql);
				}
			}
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addLikesPost() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("./data/person_likes_post_0_0.csv"));
			String thisLine = br.readLine();
			Statement stmt = this.con.createStatement();

			while ((thisLine = br.readLine()) != null) {
				thisLine = thisLine.replace("'", "''");
				String[] strings = thisLine.split("\\|");

				String sql = "INSERT INTO likes VALUES (" + strings[0] + "," + strings[1]
						+ ", TO_DATE('" + strings[2] + "','YYYY-MM-DDTHH24:MI:SS.MS+SSSS'));";
				try {
					stmt.executeUpdate(sql);
				} catch (PSQLException x) {
					System.out.println("Error@addLikesPost: " + x.getMessage() + "\n" + sql);
				}
			}
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addLikesComment() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("./data/person_likes_comment_0_0.csv"));
			String thisLine = br.readLine();
			Statement stmt = this.con.createStatement();

			while ((thisLine = br.readLine()) != null) {
				thisLine = thisLine.replace("'", "''");
				String[] strings = thisLine.split("\\|");

				String sql = "INSERT INTO likes VALUES (" + strings[0] + "," + strings[1]
						+ ", TO_DATE('" + strings[2] + "','YYYY-MM-DDTHH24:MI:SS.MS+SSSS'));";
				try {
					stmt.executeUpdate(sql);
				} catch (PSQLException x) {
					System.out.println("Error@addLikesComment: " + x.getMessage() + "\n" + sql);
				}
			}
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
