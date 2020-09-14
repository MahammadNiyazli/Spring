
<%@ page import="com.company.entity.User" %>
<%@ page import="com.company.dao.inter.CountryDaoInter" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 8/4/2020
  Time: 09:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <title>JSP Page</title>
</head>
<body>
<%

    User user = (User) request.getAttribute("user");
    CountryDaoInter countryDao = Context.instanceCountryDao();
    List<Country> listCountry = countryDao.getAllCountry();
    SkillDaoInter skillDao = Context.instanceSkillDao();
    List<Skill> listSkill = skillDao.getAllSkill();
    UserSkillDaoInter userSkilDao = Context.instanceUserSkillDao();
    List<UserSkill> listUserSkill = userSkilDao.getAllSkillByUserId(user.getId());
    EmploymentHistoryDaoInter empDao = Context.instanceEmploymentHistroyDao();
    List<EmploymentHistory> listEmp = empDao.getAllEmploymentHistoryByUserId(user.getId());
%>


<div>
    <form class="" style="margin: 35px" method="POST" action="userdetail">
        <input  type="hidden" name="id" value="<%=user.getId()%>">

        <div class="form-group" style="display: inline">
            <label for="name">Name:</label>
            <input  style="width: 125px;margin-left:10px;display: inline" class="form-control" id="name" type="text" name="name"
                   value="<%=user.getName()%>">
        </div>

        <div class="form-group" style="display: inline;margin-left: 20px">
            <label for="surname">Surname: </label>
            <input  style="width: 125px;margin-left: 10px;display: inline" class="form-control" id="surname" type="text" name="surname"
                   value="<%=user.getSurname()%>">
        </div>

        <div class="form-group" style="display: inline;margin-left: 20px">
            <input class="btn btn-outline-primary" style="display: inline;margin-top: -5px" type="submit" name="action" value="Update">
        </div>




        <div class="container" style="margin-top: 50px">


            <ul class="nav nav-tabs btn-group btn-group-justified" >
                <li class="active"><a style="margin-right: 70px;width: 225px" class="btn btn-primary" data-toggle="tab" href="#profile">Profile</a></li>
                <li><a style="margin-right: 70px;width: 225px" class="btn btn-primary" data-toggle="tab" href="#details">Details</a></li>
                <li><a style="margin-right: 70px;width: 225px" class="btn btn-primary" data-toggle="tab" href="#skills">Skills</a></li>
                <li><a style="width: 225px" class="btn btn-primary" data-toggle="tab" href="#emp">Employment History</a></li>
            </ul>

            <div class="tab-content">

                <div id="profile" class="tab-pane fade in active">
                    <div style="margin-top:36px" class="form-group">
                        <textarea class="form-control" rows="20" name="textarea"><%=user.getProfileDesc()%></textarea>
                    </div>
                </div>


                <div id="details" class="tab-pane fade">
                  <div style="margin-top: 50px">
                    <div class="form-group" >
                       <label style="width: 85px" for="address">Address</label>
                        <input  style="width: 225px;display: inline"
                                class="form-control" type="text" id="address" name="address"
                                value="<%=user.getAddress()%>">
                    </div>

                    <div class="form-group" >
                        <label style="width: 85px" for="email">Email</label>
                        <input  style="width: 225px;display: inline"
                                class="form-control" id="email" type="text" name="email"
                                value="<%=user.getEmail()%>">
                    </div>

                    <div class="form-group" >
                       <label style="width: 85px" for="phone">Phone</label>
                        <input  style="width: 225px;display: inline" class="form-control" id="phone" type="text" name="phone"
                                value="<%=user.getPhone()%>">
                    </div>

                    <div class="form-group" >
                        <label style="width: 85px" for="birthdate">Birthdate</label>
                        <input  style="width: 225px;display: inline" class="form-control" type="text" id="birthdate" name="birthdate"
                                value="<%=user.getBirthDate()%>">
                    </div>

                      <div class="form-group">
                          <label style="width: 85px" for="birthplace">Birthplace</label>
                          <select class="form-control" id="birthplace" style="width: 225px;display: inline">
                              <%for (Country c : listCountry){
                               if(c.getName().equals(user.getBirthPlace().getName())){%>
                               <option selected><%=c%></option>
                               <%}else{%>
                              <option><%=c%></option>
                              <%}}%>
                          </select>
                      </div>

                      <div class="form-group">
                          <label style="width: 85px" for="nationality">Nationality</label>
                          <select name="nationality" class="form-control" id="nationality" style="width: 225px;display: inline">
                              <%for (Country c : listCountry){
                                  if(c.getNationality().equals(user.getNationality().getNationality())){%>
                              <option selected><%=c%></option>
                              <%}else{%>
                              <option><%=c%></option>
                              <%}}%>
                          </select>
                      </div>
                </div>
                </div>


                <div id="skills" class="tab-pane fade">
                    <div style="margin-top: 50px">
                        <div class="form-group" style="display: inline">
                            <label style="width:40px" for="skill">Skill</label>
                            <select name="skill" class="form-control" id="skill" style="width: 150px;display: inline">
                                <%for (Skill s : listSkill){%>
                                <option><%=s%></option>
                                <%}%>
                            </select>
                        </div>

                        <div class="form-group" style="display: inline;margin-left: 25px">
                            <input  style="width: 150px;display: inline" class="form-control" id="skillTxt" type="text" name="skillTxt"
                                    value="">
                        </div>

                        <div class="form-group" style="margin-left: 25px;margin-top: 30px;">
                            <input class="btn btn-info" style="display: inline;width: 60px;margin-right: 15px;font-size: 20px" class="form-control" id="plus" type="submit" name="plus" value="+">
                            <input class="btn btn-danger" style="display: inline;width: 60px;font-size: 20px" class="form-control" id="minus" type="submit" name="minus" value="-">
                        </div>

                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>Skill</th>
                                <th>Power</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%for (UserSkill us : listUserSkill){%>
                             <tr>
                                 <td><%=us.getSkill()%></td>
                                 <td><%=us.getPower()%></td>
                             </tr>
                               <%}%>
                            </tbody>
                        </table>

                    </div>
                </div>


                <div id="emp" class="tab-pane fade">
                   <div style="margin-top: 50px">
                       <div class="form-group" style="display: inline" >
                           <label style="width: 135px" for="header">Birthdate</label>
                           <input  style="width: 225px;display: inline;margin-right: 30px" class="form-control" type="text" id="header" name="header"
                                   value="">
                       </div>

                       <div class="form-group" style="display: inline" >
                           <label style="width: 85px" for="begindate">Begindate</label>
                           <input  style="width: 225px;display: inline" class="form-control" type="text" id="begindate" name="begindate"
                                   value="">
                       </div>
                       <br>
                       <br>
                       <div class="form-group" style="display: inline" >
                           <label style="width: 135px" for="jobdesc">Job Description</label>
                           <input  style="width: 225px;display: inline;margin-right: 30px" class="form-control" type="text" id="jobdesc" name="jobdesc"
                                   value="">
                       </div>

                       <div class="form-group" style="display: inline" >
                           <label style="width: 85px" for="enddate">Enddate</label>
                           <input  style="width: 225px;display: inline" class="form-control" type="text" id="enddate" name="enddate"
                                   value="">
                       </div>

                       <div class="form-group" style="margin-left: 25px;margin-top: 30px;">
                           <input class="btn btn-info" style="display: inline;width: 150px;margin-right: 15px;" class="form-control" id="add" type="submit" name="add" value="Add">
                           <input class="btn btn-danger" style="display: inline;width: 150px;" class="form-control" id="remove" type="submit" name="remove" value="Remove">
                       </div>


                       <table class="table table-hover">
                           <thead>
                           <tr>
                               <th>Header</th>
                               <th>Job Description</th>
                               <th>Begindate</th>
                               <th>Enddate</th>
                           </tr>
                           </thead>
                           <tbody>
                           <%for (EmploymentHistory emp : listEmp){%>
                           <tr>
                               <td><%=emp.getHeader()%></td>
                               <td><%=emp.getJobDescription()%></td>
                               <td><%=emp.getBeginDate()%></td>
                               <td><%=emp.getEndDate()%></td>
                           </tr>
                           <%}%>
                           </tbody>
                       </table>

                   </div>

                </div>


            </div>
        </div>

    </form>
</div>

</body>
</html>
