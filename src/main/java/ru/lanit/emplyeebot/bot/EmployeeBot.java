package ru.lanit.emplyeebot.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.lanit.emplyeebot.entities.Office;
import ru.lanit.emplyeebot.repos.OfficeRepo;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class EmployeeBot extends TelegramLongPollingBot {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeBot.class);

    @Autowired
    private OfficeRepo officeRepo;

    @Value("${bot.token}")
    private String token;

    @Value("${bot.username}")
    private String username;

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            switch (update.getMessage().getText()) {
                case "/start": {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(update.getMessage().getChatId());

                    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                    List<InlineKeyboardButton> buttonsList = new ArrayList<>();

                    for (Office office : officeRepo.findAll()) {
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setCallbackData(office.getId().toString());

                        buttonsList.add(button.setText(office.getName()));
                    }
                    List<List<InlineKeyboardButton>> buttons = Arrays.asList(buttonsList);
                    inlineKeyboardMarkup.setKeyboard(buttons);

                    sendMessage.setText("Выберите офис");
                    sendMessage.setReplyMarkup(inlineKeyboardMarkup);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                break;
                case "/mainmenu": {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(update.getMessage().getChatId());

                    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                    List<List<InlineKeyboardButton>> allButtons = new ArrayList<>();

                    List<String> options = Arrays.asList("Сотруднику",
                            "Подать заявку",
                            "Транспорт",
                            "Поиск сотрудника офиса",
                            "Правила офиса",
                            "Редактировать данные");
                    for (String option : options) {
                        List<InlineKeyboardButton> listRow = new ArrayList<>();
                        InlineKeyboardButton buttonInRow = new InlineKeyboardButton();
                        buttonInRow.setCallbackData(option);

                        listRow.add(buttonInRow.setText(option));
                        allButtons.add(listRow);
                    }
                    inlineKeyboardMarkup.setKeyboard(allButtons);

                    sendMessage.setText("Выберите опцию");
                    sendMessage.setReplyMarkup(inlineKeyboardMarkup);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;
                }

            }
        }
        if (update.getCallbackQuery() != null) {
            switch (update.getCallbackQuery().getData()){
                case "Сотруднику": {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(update.getCallbackQuery().getFrom().getId().toString());

                    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                    List<List<InlineKeyboardButton>> allButtons = new ArrayList<>();

                    List<String> options = Arrays.asList("О компании",
                            "О департаменте",
                            "Заказ кадровых справок",
                            "Отпуск",
                            "Календарь событий",
                            "Jira: поддерживаемые сервисы",
                            "Административные кадровые вопросы",
                            "Project: списание рабочего времени",
                            "Давай общаться",
                            "ДКС читает",
                            "Обучение в ДКС",
                            "Скидки сотрудникам ДКС");
                    for (String option : options) {
                        List<InlineKeyboardButton> listRow = new ArrayList<>();
                        InlineKeyboardButton buttonInRow = new InlineKeyboardButton();
                        buttonInRow.setCallbackData(option);

                        listRow.add(buttonInRow.setText(option));
                        allButtons.add(listRow);
                    }
                    inlineKeyboardMarkup.setKeyboard(allButtons);

                    sendMessage.setText("Выберите опцию");
                    sendMessage.setReplyMarkup(inlineKeyboardMarkup);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }



    @PostConstruct
    public void start() {
    }
}
