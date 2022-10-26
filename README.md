# TestProject_Matchmaker


Тестовый проект.
Набросок балансера для подбора игроков в группу.
Принимает пакеты POST с JSON в теле вида:
{
    "name": "Sveta",
    "skill": 30,
    "latency": 250
}

Подбирает игроков на основе того, не увеличит ли их добавление в группу standard deviation для параметров skill и latency сверх заданного в параметрах (matchmaker.properties) предела.

TODO:
Добавить систему смягчения требований по параметрам, если время ожидания игроков в группе слишком большое (пусть лучше играют с кем-то более сильным, чем продолжают скучать в очереди). Потребует сделать так, чтобы была проверка игроков из других групп, не подходят ли они для данной группы после смягчения требования. Игроков нужно будет отсортировать по тому, насколько они близки к игрокам внутри игруппы, и выбрать самых близких.