@Controller
@RequiredArgsConstructor
@RequestMapping("/predictions")
public class TEMPViewController {

    private final PredictionService predictionService;
    private final GameService gameService;

    // --- NOWA PREDYKCJA ---
    @GetMapping("/new/{gameId}")
    public String showCreateForm(@PathVariable Long gameId, Model model) {
        PredictionDTO prediction = new PredictionDTO();
        prediction.setGameId(gameId);

        model.addAttribute("prediction", prediction);
        model.addAttribute("game", gameService.getGameById(gameId));
        return "predictions/new";  // osobny widok
    }

    @PostMapping("/new/{gameId}")
    public String create(@PathVariable Long gameId,
                         @Valid @ModelAttribute("prediction") PredictionDTO dto,
                         BindingResult result,
                         Model model,
                         RedirectAttributes ra) {
        if (result.hasErrors()) {
            model.addAttribute("game", gameService.getGameById(gameId));
            return "predictions/new";
        }
        predictionService.createPrediction(dto);
        ra.addFlashAttribute("message", "Predykcja dodana");
        return "redirect:/games";
    }

    // --- EDYCJA PREDYKCJI ---
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        PredictionDTO prediction = predictionService.getPredictionById(id);

        model.addAttribute("prediction", prediction);
        model.addAttribute("game", gameService.getGameById(prediction.getGameId()));
        return "predictions/edit";  // osobny widok
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("prediction") PredictionDTO dto,
                         BindingResult result,
                         Model model,
                         RedirectAttributes ra) {
        if (result.hasErrors()) {
            model.addAttribute("game", gameService.getGameById(dto.getGameId()));
            return "predictions/edit";
        }
        predictionService.updatePrediction(id, dto);
        ra.addFlashAttribute("message", "Predykcja zaktualizowana");
        return "redirect:/games";
    }

    // --- USUWANIE ---
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        predictionService.deletePrediction(id);
        ra.addFlashAttribute("message", "Predykcja usunięta");
        return "redirect:/games";
    }
}