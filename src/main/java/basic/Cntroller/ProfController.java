package basic.Cntroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import basic.CouchDao.GroupeDao;
import basic.CouchDao.ProfRepository;
import basic.Dto.ProfDto;
import basic.Dto.RespenseProf;
import basic.Dto.ResponseGroupe;
import basic.Dto.ResponsePfeDto;
import basic.Dto.ResponsePfeList;
import basic.Service.ServiceProf;
import basic.module.PfeInfo;
import basic.module.Prof;
import  basic.chat.app.server.model.User;

@RestController
@RequestMapping("Professeur")
public class ProfController {
	@Autowired()
	ServiceProf serviceprof;
	@Autowired()
	ProfRepository profdao;
	
	@PostMapping(value="", consumes = MediaType.APPLICATION_JSON_VALUE)
	Prof Add(@RequestBody()ProfDto prof ) {
		
		System.out.println(prof.toString());
		System.out.println("a");
		return  serviceprof.saveProf(prof);
		
	}

	@GetMapping("id/{id}")
	Prof selectProf(@PathVariable("id")Integer id){
		System.out.println(id);
		return profdao.getById(id);
		
		
	}
	@GetMapping("pfe/{id}")
	List<ResponsePfeList> selectListPfe(@PathVariable("id")String id){
		return serviceprof.listpfe(id);
	}
	  @GetMapping("groupe/{id}")
	List<ResponseGroupe> getGroupe(@PathVariable("id")String email){
		  return serviceprof.getGroupes(email);
	  }
	  
	  @GetMapping("listUsers")
	  List<User> getUserGroup(String username){
		  System.out.println(username);
		  return serviceprof.getUsersgroup(username);
	  }
	
}
