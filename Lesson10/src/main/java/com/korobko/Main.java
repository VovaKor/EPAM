package com.korobko;

import com.korobko.models.Characteristics;
import com.korobko.models.Paper;
import com.korobko.models.PaperStore;
import com.korobko.models.PeriodicalType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

import static com.korobko.utils.Constants.PATH_TO_XML;

/**
 * 1. Создать файл XML и соответствующую ему схему XSD.
 * 2. При разработке XSD использовать простые и комплексные типы,
 * перечисления, шаблоны и предельные значения, обязательно использование
 * атрибутов и типа ID.
 * 3. Сгенерировать (создать) Java-класс, соответствующий данному описанию.
 * 4. Создать Java-приложение для разбора XML-документа и инициализации
 * коллекции объектов информацией из XML-файла. Для разбора использовать
 * SAX, DOM и StAX парсеры. Для сортировки объектов использовать интерфейс
 * Comparator.
 * 5. Произвести проверку XML-документа с привлечением XSD.
 * 6. Определить метод, производящий преобразование разработанного
 * XML-документа в документ, указанный в каждом задании.
 * <p>
 * 11. Периодические издания.
 * Title – название издания.
 * Type – тип издания (газета, журнал, буклет).
 * Monthly – ежемесячное либо нет.
 * Chars (должно быть несколько) – характеристики: цветное (да либо нет),
 * объем (n страниц), глянцевое (да [только для журналов и буклетов]
 * либо нет [для газет]), имеет подписной индекс (только для газет и
 * журналов).
 * Корневой элемент назвать Paper.
 *
 * стр 767 log4j JAVA_Methods_Programming_v2.march2015
 * в таск 3 xml добавить свои Exception
 * логирование в файл уровни INFO и ERROR
 *
 * @author Vova Korobko
 */
public class Main {

    public static void main(String[] args) {

    }

}
