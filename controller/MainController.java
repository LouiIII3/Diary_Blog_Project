@Controller
@RequestMapping("/web")
//@ResponseBody
public class MainController {

    @GetMapping("/")
    public String mainP() {

        return "login";
    }

    @GetMapping("/account")
    public String account(Model model) {
        return "account";
    }

    @GetMapping("/main")
    public String newPage(Model model) {
        return "new"; //
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/diary")
    public String showDiaryPage(Model model) {
            return "diary";
    }


    @GetMapping("/diary2")
    public String createDiaryPage(Model model) {
        return "diary2";
    }

    }
