package ru.anohin.home.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.anohin.home.model.User;
import ru.anohin.home.services.UserService;


@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String allUsers(Model model){
        model.addAttribute("allusers", userService.allUsers());
        return "/all-users";
    }

    //При переходе на страницу отображаем форму для ввода данных. Модель user
    @GetMapping("/addnewuser")
    public String newUser(Model model) {
        model.addAttribute("addnewuser", new User());
        return "/add-new-user";
    }

    //Отправляем ПОСТ запрос для модели user, добавляем в базу данных через сервис,
    //производим переход на страницу users(редирект вызывает контроллер
    @PostMapping
    public String create(@ModelAttribute("addnewuser") User user) {
        userService.add(user);
        return "redirect:/";
    }


    @GetMapping("/edituserbyid")
    public String editUserView(@RequestParam("id") Long id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("edituser", user);
        return "/edit-user";
    }

    @PostMapping("/userupdate")
    public String updateUser(@ModelAttribute("updateuser") User user) {
        userService.edit(user);
        return "redirect:/";
    }


    @GetMapping("/userdelete")
    public String deleteUser(@ModelAttribute("deleteuser") User user) {
        userService.delete(user);
        return "redirect:/";
    }

}
