package study.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import common.Attachment;
import shop.model.vo.Purchase;
import study.model.vo.MemberJoinStudy;
import study.model.vo.PageInfo;
import study.model.vo.Study;


public class StudyDao {

	private Properties query = new Properties();
	
	public StudyDao() {
		String fileName = StudyDao.class.getResource("/sql/study/study-query.xml").getPath();
		
		try {
			query.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// 스터디방 리스트 조회
	public List<Study> selectStudyList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Study> StudyList = new ArrayList<>();
		String sql = query.getProperty("selectStudyList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				StudyList.add(new Study(rset.getInt("s_no"),
										rset.getString("s_name"),
										rset.getInt("s_to"),
										rset.getString("s_day"),
										rset.getTimestamp("s_startPeriod"),
										rset.getTimestamp("s_endPeriod"),
										rset.getTimestamp("s_startTime"),
										rset.getTimestamp("s_endTime"),
										rset.getString("s_explain"),
										rset.getString("s_notice"),
										rset.getString("cname"),
										rset.getString("nickname"),
										rset.getString("change_name"),
										rset.getString("file_path"),
										rset.getInt("studyMemberNum")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return StudyList;
	}
	
	// 스터디방별 가입된 회원수 조회
	public List<Study> selectStudyMemberList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset=null;
		List<Study> StudyMemberList = new ArrayList<>();
		
		String sql = query.getProperty("selectStudyMemberList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				StudyMemberList.add(new Study(rset.getInt("s_no"),
											  rset.getInt("s_memberCount")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return StudyMemberList;
	}
	
	// 스터디방 생성
	public int insertStudy(Connection conn, Study s) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertStudyRoom");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, s.getS_name());
			pstmt.setInt(2, s.getS_to());
			pstmt.setString(3, s.getS_day());
			
			pstmt.setTimestamp(4, new Timestamp(s.getS_startPeriod().getTime()));
			pstmt.setTimestamp(5, new Timestamp(s.gets_endPeriod().getTime()));
			pstmt.setTimestamp(6, new Timestamp(s.getS_startTime().getTime()));
			pstmt.setTimestamp(7, new Timestamp(s.gets_endTime().getTime()));
					
//			System.out.println("new Timestamp(s.getS_startTime().getTime())"+new Timestamp(s.getS_startTime().getTime()));
//			System.out.println("new Timestamp(s.getS_startTime().getHours())"+new Timestamp(s.getS_startTime().getHours()));
//			System.out.println("new Timestamp(s.getS_startTime().getMinutes())"+new Timestamp(s.getS_startTime().getMinutes()));
//			System.out.println("new Timestamp(s.getS_startTime().getHours()+s.getS_startTime().getMinutes())"+new Timestamp(s.getS_startTime().getHours()+s.getS_startTime().getMinutes()));
			
			pstmt.setString(8, s.getS_explain());
			pstmt.setString(9, s.getS_notice());
			pstmt.setInt(10, s.getUser_no());
			pstmt.setInt(11, s.getCid());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	// 스터디방 첨부이미지 attachment테이블에 insert
	public int insertStudyPhoto(Connection conn, List<Attachment> getsImgList) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertStudyPhoto");
		
		try {
			Attachment at = getsImgList.get(0);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, at.getOrigin_name());
			pstmt.setString(2, at.getChange_name());
			pstmt.setString(3, at.getFile_path());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	// 스터디방 1개 조회(s_no)이용
	public Study selectStudyRoom(Connection conn, int s_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Study s = null;
		int count=0;
		String sql = query.getProperty("selectStudyRoom");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, s_no);
			
			rset=pstmt.executeQuery();
			
			while (rset.next()) {
				if (count++ < 1) {
					//스터디방 정보는 첫번째 반복에서만 처리(중복)
					s = new Study();
					s.setS_no(rset.getInt("s_no"));
					s.setS_name(rset.getString("s_name"));
					s.setS_to(rset.getInt("s_to"));
					s.setS_day(rset.getString("s_day"));
					s.setS_startPeriod(rset.getTimestamp("s_startPeriod"));
					s.sets_endPeriod(rset.getTimestamp("s_endPeriod"));
					s.setS_startTime(rset.getTimestamp("s_startTime"));
					s.sets_endTime(rset.getTimestamp("s_endTime"));
					s.setS_explain(rset.getString("s_explain"));
					s.setS_notice(rset.getString("s_notice"));
					s.setUser_no(rset.getInt("user_no"));
					s.setCname(rset.getString("cname"));
					s.setUser_nkname(rset.getString("nickname"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return s;
	}
	// 스터디방 삭제
	public int deleteStudy(Connection conn, int s_no) {
		PreparedStatement pstmt=null;
		int result=0;
		
		String sql=query.getProperty("deleteStudy");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, s_no);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	// 스터디방 총 갯수 구하기
	public int selectStudyListNumber(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int studyListCount = 0;
		
		String sql = query.getProperty("selectStudyListNumber");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				studyListCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return studyListCount;
	}

	// 스터디방 가입
	public int insertMemberJoinStudy(Connection conn, MemberJoinStudy mjs) {
		PreparedStatement pstmt=null;
		int result = 0;
		String sql = query.getProperty("insertMemberJoinStudy");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, mjs.getUserNo());
			pstmt.setInt(2, mjs.getS_no());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 스터디방 가입 여부 조회
	public MemberJoinStudy selectMemberJoinStudy(Connection conn, int userNo, int s_no) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		String sql = query.getProperty("selectMemberJoinStudy");
		MemberJoinStudy mjs= null;
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, userNo);
			pstmt.setInt(2, s_no);
			
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				mjs = new MemberJoinStudy();
				
				mjs.setUserNo(userNo);
				mjs.setS_no(s_no);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return mjs;
	}

	// 멤버별 가입된 스터디방의 수
	public int memberJoinStudyNum(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset=null;
		int result=0;
		
		String sql = query.getProperty("memberJoinStudyNum");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, userNo);

			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				result = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	// 스터디방별 가입된 회원수
	public int StudyMemberCount(Connection conn, int s_no) {
		PreparedStatement pstmt = null;
		ResultSet rset=null;
		int result=0;
		
		String sql = query.getProperty("StudyMemberCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, s_no);

			rset=pstmt.executeQuery();
			if (rset != null) {
				while (rset.next()) {
					result = rset.getInt(1);
				}
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	// 회원별 가입한 스터디방 제목, 인원수, 정원, 카테고리 조회
    public List<Study> selectMyStudy(Connection conn, int userNo) {
       PreparedStatement pstmt = null;
       ResultSet rset = null;
       List<Study> resultList = new ArrayList<>();
       String sql = query.getProperty("selectMyStudy");
       
       try {
          pstmt = conn.prepareStatement(sql);
          
          pstmt.setInt(1, userNo);
          
          rset = pstmt.executeQuery();
          while(rset.next()) {
             resultList.add(new Study(rset.getInt("s_no"),
                     rset.getString("s_name"),
                     rset.getInt("s_to"),
                     rset.getString("s_day"),
                     rset.getString("cname"),
                     rset.getString("change_name"),
                     rset.getString("file_path"),
                     rset.getInt("studyMemberNum")
                     ));
          }
          
       } catch (SQLException e) {
          e.printStackTrace();
       } finally {
          close(rset);
          close(pstmt);
       }
       return resultList;
    }

    // 유저번호로 만든 스터디방 가져오기(가장최근에 만든 1개)
	public Study selectStudyRoomOnlySNo(Connection conn, int user_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Study s = null;
		int count = 0;
		
		String sql = query.getProperty("selectStudyRoomOnlySNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, user_no);
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				if(count++ < 1) {
					s = new Study();
					s.setS_no(rset.getInt("s_no"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return s;
	}
	
	// 로그인한 유저넘버로 가입한 스터디방 갯수 가져오기
	public MemberJoinStudy userJoinStudyNum(Connection conn, int user_no) {
		PreparedStatement pstmt = null;
		ResultSet rset= null;
		MemberJoinStudy mjs = null;
		
		String sql = query.getProperty("userJoinStudyNum");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, user_no);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				mjs = new MemberJoinStudy();
				mjs.setUserJoinStudyNum(rset.getInt("userJoinStudyNum"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return mjs;
	}

	// 페이징
	public List<Study> selectList1(Connection conn, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Study> StudyList = new ArrayList<>();
		String sql = query.getProperty("selectList1");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getPage() - 1 ) * 10 + 1 ;
			int endRow = startRow + 10 - 1 ;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				StudyList.add(new Study(rset.getInt("s_no"),
										rset.getString("s_name"),
										rset.getInt("s_to"),
										rset.getString("s_day"),
										rset.getTimestamp("s_startPeriod"),
										rset.getTimestamp("s_endPeriod"),
										rset.getTimestamp("s_startTime"),
										rset.getTimestamp("s_endTime"),
										rset.getString("s_explain"),
										rset.getString("s_notice"),
										rset.getString("cname"),
										rset.getString("nickname"),
										rset.getString("change_name"),
										rset.getString("file_path"),
										rset.getInt("studyMemberNum")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return StudyList;
	}

	public List<Study> selectList1(Connection conn, PageInfo pi, String keyword, String category, String canJoin) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Study> StudyList = new ArrayList<>();
		String sql = query.getProperty("selectStudyList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getPage() - 1 ) * 10 + 1 ;
			int endRow = startRow + 10 - 1 ;
			
//			pstmt.setInt(1, startRow);
//			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			int num = 0;
			
			while(rset.next()) {
				
				Study study = new Study(rset.getInt("s_no"),
						rset.getString("s_name"),
						rset.getInt("s_to"),
						rset.getString("s_day"),
						rset.getTimestamp("s_startPeriod"),
						rset.getTimestamp("s_endPeriod"),
						rset.getTimestamp("s_startTime"),
						rset.getTimestamp("s_endTime"),
						rset.getString("s_explain"),
						rset.getString("s_notice"),
						rset.getString("cname"),
						rset.getString("nickname"),
						rset.getString("change_name"),
						rset.getString("file_path"),
						rset.getInt("studyMemberNum"));
				
				if(study.getS_name().contains(keyword) && study.getStudyMemberNum() < study.getS_to()) {
					if (!category.equals("default") && study.getCname().equals(category.toLowerCase())) {
						num++;
						if (startRow <= num && num <= endRow) {
							StudyList.add(study);
						}
						
						
					} else if (category.equals("default")) {
						num++;
						if (startRow <= num && num <= endRow) {
							StudyList.add(study);
						}
//						StudyList.add(study);
					}
				}
				
//				StudyList.add(new Study(rset.getInt("s_no"),
//										rset.getString("s_name"),
//										rset.getInt("s_to"),
//										rset.getString("s_day"),
//										rset.getTimestamp("s_startPeriod"),
//										rset.getTimestamp("s_endPeriod"),
//										rset.getTimestamp("s_startTime"),
//										rset.getTimestamp("s_endTime"),
//										rset.getString("s_explain"),
//										rset.getString("s_notice"),
//										rset.getString("cname"),
//										rset.getString("nickname"),
//										rset.getString("change_name"),
//										rset.getString("file_path"),
//										rset.getInt("studyMemberNum")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return StudyList;
	}
	 
	public int snameCheck(Connection conn, String sname) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String sql = query.getProperty("snameCheck");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sname);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
	
		return result;
	}

	//유저가 구매한 상품의 유효기간의 값.
    public Purchase purchaseLimit(Connection conn, int user_no) {
         PreparedStatement pstmt = null;
           ResultSet rset = null;
           Purchase result = null;
           
           String sql = query.getProperty("purchaseLimit");
           
           try {
              pstmt = conn.prepareStatement(sql);
              
              pstmt.setInt(1, user_no);
      
              rset = pstmt.executeQuery();
             while(rset.next()){
                result = new Purchase(rset.getInt("MAX(PR.S_LIMIT)"),
                                 rset.getInt("MAX(PR.S_TO_LIMIT)"),
                                 rset.getInt("MAX(PR.TODO_LIMIT)"),
                                 rset.getInt("MAX(PR.S_LIMIT_DATE)"));
             
              }  
           } catch (SQLException e) {
              e.printStackTrace();
           } finally {
              close(rset);
              close(pstmt);
           }
       
           return result;
    }


	
	public int insertTime(Connection conn, int loginUserNo, int s_no, int dbSaveTime) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertTime");
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, loginUserNo);
			pstmt.setInt(2, s_no);
			pstmt.setInt(3, dbSaveTime);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	// 스터디방 내 회원 강퇴
	public int deleteMemberJoinStudy(Connection conn, int s_no, int user_no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("deleteMemberJoinStudy");
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, s_no);
			pstmt.setInt(2, user_no);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
		}
		
		return result;
	}


	
	

}









