/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.entities.LecturerWorkload;
import dao.entities.ResultLecture;
import dao.entities.Student;
import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author nastja
 */
public interface ResultLectureDao {
    
   List<ResultLecture> findByLecWork(LecturerWorkload lw, Date date);
   
   ResultLecture create(ResultLecture rl);
   
   void createEmpty(ResultLecture rl, List<Student> students);
   
   List<Date> findDateByAttest(LecturerWorkload lw , Date date);
   
   List<Integer> findByDateLecWork(LecturerWorkload lw, Date date);

   int findSumMaxMark(LecturerWorkload lw, Date date);
   
   Map<Date, Integer> findAllMaxMark(LecturerWorkload lw, Date date);
   
   void deleteByDate(Date date, LecturerWorkload l);

   void update(Date oldDate, LecturerWorkload lecturerWorkload, int newMark, Date newDate);

    public void updateMark(List<ResultLecture> changeList);
 
   
   
}
