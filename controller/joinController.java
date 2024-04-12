@RestController
@ResponseBody
public class JoinController {

    private final JoinService joinService;

    public JoinController(JoinService joinService) {

        this.joinService = joinService;
    }

    @PostMapping("/join")
    public ResponseEntity<String> joinProcess(@RequestBody JoinDTO joinDTO) {
        String resultMessage = joinService.joinProcess(joinDTO);
        return ResponseEntity.ok(resultMessage);
    }
    
}
