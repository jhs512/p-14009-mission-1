package com.back;

import com.back.controller.WiseSayingController;
import com.back.repository.WiseSayingRepository;
import com.back.service.WiseSayingService;
import com.back.view.InputView;
import com.back.view.OutputView;

public class Application {
    public static void main(String[] args) {
        WiseSayingController wiseSayingController = new WiseSayingController(
                    new InputView(), new OutputView(), new WiseSayingService(), new WiseSayingRepository()
        );
    }
}
