package pl.xxx.demo.ViewController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.xxx.demo.Prediction.PredictionRequestDTO;
import pl.xxx.demo.Prediction.PredictionResponseDTO;
import pl.xxx.demo.Prediction.PredictionService;
import pl.xxx.demo.Game.GameResponseDTO;
import pl.xxx.demo.Game.GameService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/predictions")
public class TEMPViewController {

    private final PredictionService predictionService;
    private final GameService gameService;

    // TWORZENIE NOWEJ PREDYKCJI
    @GetMapping("/new")
    public String showCreatePredictionForm(@RequestParam Long gameId, Model model) {
        GameResponseDTO game = gameService.get(gameId);
        PredictionRequestDTO prediction = new PredictionRequestDTO();
        prediction.setGameId(gameId);

        model.addAttribute("prediction", prediction);
        model.addAttribute("game", game);
        return "predictions/create-prediction";
    }

    @PostMapping("/new")
    public String createPrediction(@Valid @ModelAttribute("prediction") PredictionRequestDTO predictionDTO,
                                   BindingResult result, Model model, RedirectAttributes ra) {
        if (result.hasErrors()) {
            // Ponownie załaduj game, bo po błędzie nie ma go w modelu
            GameResponseDTO game = gameService.get(predictionDTO.getGameId());
            model.addAttribute("game", game);
            return "predictions/create-prediction";
        }

        predictionService.add(predictionDTO);
        ra.addFlashAttribute("message", "Predykcja dodana pomyślnie");
        return "redirect:/games";
    }

    // EDYCJA ISTNIEJĄCEJ PREDYKCJI
    @GetMapping("/edit")
    public String showEditPredictionForm(@RequestParam Long id, Model model) {
        PredictionResponseDTO prediction = predictionService.get(id);
        GameResponseDTO game = gameService.get(prediction.getGameId());

        model.addAttribute("prediction", prediction);
        model.addAttribute("game", game);
        return "predictions/edit-prediction";
    }

    @PostMapping("/edit")
    public String updatePrediction(@RequestParam Long id,
                                   @Valid @ModelAttribute("prediction") PredictionRequestDTO predictionDTO,
                                   BindingResult result, Model model, RedirectAttributes ra) {
        if (result.hasErrors()) {
            // Ponownie załaduj game i prediction, bo po błędzie nie ma ich w modelu
            PredictionResponseDTO existingPrediction = predictionService.get(id);
            GameResponseDTO game = gameService.get(existingPrediction.getGameId());
            model.addAttribute("game", game);
            model.addAttribute("prediction", existingPrediction);
            return "predictions/edit-prediction";
        }

        predictionService.update(id, predictionDTO);
        ra.addFlashAttribute("message", "Predykcja zaktualizowana pomyślnie");
        return "redirect:/games";
    }

    // USUWANIE PREDYKCJI
    @PostMapping("/delete")
    public String deletePrediction(@RequestParam Long id, RedirectAttributes ra) {
        predictionService.delete(id);
        ra.addFlashAttribute("message", "Predykcja usunięta pomyślnie");
        return "redirect:/games";
    }
}