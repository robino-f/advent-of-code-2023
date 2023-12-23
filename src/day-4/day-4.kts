import kotlin.math.pow


//    val data = listOf(
//            "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53",
//            "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19",
//            "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1",
//            "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83",
//            "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36",
//            "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11"
//    )
val data = listOf(
        "Card   1: 30 51 48 31 36 33 49 83 86 17 | 17 33 31 70 90 37 86 45 58 21 83 52 59 68 55 32 20 43 48 75 30 42 80 60 71",
        "Card   2: 83 45 32 60 10 94 13 29 52 43 | 47 15 94 32 13 64  4 48 20 83 52 75 41 50 60 14 45 43 37 29 35 10 89 77 25",
        "Card   3: 15  1  9 91 24 53 88 45 60 82 | 86 92 57 55 85  2 58 66 25 38 43 76 39 62 64 54 87 35 27 99 42 36 56 40 47",
        "Card   4: 49 19 86  1 45 62 58 42 22 72 | 58 45 69 80 96 56 72 71 42 26  1 22 49 93 86 39 19 68 38 48 67  5 61 62 28",
        "Card   5: 42  8 10 71 79 76 64 96 23 50 | 59 61 62 53 86 50 67 23 37 47 73  4 20 64 14 76  9 24 71 66 89 21 94 28 13",
        "Card   6: 87 61 26 33 52  9 21 18 74 81 | 20 33 57 14 28 21 47 85 83 16 52 60 41 23 90 75 54 35 61 88 69 64 37 50 43",
        "Card   7:  2 48 64 63  8 19 80 93 89 52 | 68 19 32 55  2 42 24 70 43 41 90 48 76 33 45 66 28  3 46 81 82 30 50 96 54",
        "Card   8: 55  8 92 82 74 40  4 25 43 30 |  8 35 81 64 74 36 30 21 92 43 67 31 10 97 79 39 94 89 51 82  4 25 59 48 29",
        "Card   9: 11 34  9  5 26 12 86 79 39 92 | 35 71 53 88  4 28 12 83 37 16 81 97 40 43 55 27  5 52 44 30 90 78 74 21  6",
        "Card  10: 48 82 86 76 99 83 52 55 37 42 | 56 96 64 57 77 20 78 15 19 40 97 52 33 28 86 18 38 72 13 60 89 69 58 17  8",
        "Card  11:  8 43 79 25 45  5 64 90 18 96 |  6 86 76 87 68 72 94  9 82 16 61 57 50 95 10 67 33 88 54 15 26 22 85  4 69",
        "Card  12: 47 26 95 84 97 48 73 32 94 83 | 75 89 87 86 58 12 73 17 19 50 60 16 95 43 27  7  8 26 37 22 15 47 10 63 64",
        "Card  13: 67 94 51 31  2  7 60 52 87 27 | 50 10 68  3 37 18 66 19 20  6 79 55 77  1 91 88 58 39 65 48 32 80 17 56 29",
        "Card  14: 59 35 83 36 26 72 12 37  5 51 | 52 32 59 27 36 90 39 53 22 61 99  6 56 76  3 83 82 92 31 78 63 87 24 74 10",
        "Card  15: 66 36 60 28 95 44  6 81  4 21 | 47 53 44 41 89 79  6 20 80 88 13 29  2 91 52 96 69 10 65 84 82 92 35 57 51",
        "Card  16: 44 73  5 12 34 39 18 30 66  6 | 87 58 51 68 90 95 37 75 20 27  8 49 52 55 25 91 60 76 19 16 40 15 99 41 56",
        "Card  17: 71 70 54 29 18 21 36 47 33 78 | 42 45  2 67 91 39 25 58 74 68 15  5 33 12 62 80 76 72  8 81 51 19 85 60  1",
        "Card  18: 50  8 41 74 97 80 76 23 83 58 | 36 91 95 92 69 54 18 47 22 46 73 77 35 87 40 98 84 68  7 29 38 32 39 71 82",
        "Card  19: 68 26 13 33 40 49 30 50 99 47 | 26 65 49 61 82 54 45 21 67 58 86 88 63 23 44 85 57 92 87 51 78 50 40 39 14",
        "Card  20: 95 47 40 82 11 74 81 86 89 67 | 83 10 52 70  8 89 85 36 37 47 86 16 51 92 41 62 82 69 81 90 57 79 88 74 65",
        "Card  21: 70 27 99 79 11 65 48 72 96 28 | 72 14 92  8 70 65 32 80 99 50 48  7 96 79 28 77 85 98 11 78 66 19 35 91 27",
        "Card  22:  7 59  2 60 40 42  4 16 61 96 | 85  4  5 89  8  7 66 67 61 14 34 46 15 26 42 21 94 44 59 16 96 45 49 80 73",
        "Card  23: 51 33 80 81 53 97 30 96 77 43 | 65 37  7 80  2 32 36 62 51 47 43 81 49 96 69 22 53 30 84 19 97 33 77 24 95",
        "Card  24: 12 56 85  5 31 57 35 65 79 27 | 94  5 91 11 74 35 71 31 62 78 25  6 27 12 47 36 85 45 56 89 79 16 93 65 57",
        "Card  25: 34 90 88  1 84 87 60 33 81 78 | 33 99 78 16 87 24 40 90 34 79 51 60 81  6 38 92 25  5 84 41  1 11 88 42  2",
        "Card  26: 27 98 19 70 86 41 76 78 22 29 | 10 41 23 29 73 65  6 30 27 78 76 12 22 98 47 59 86 79 35 19 70 46 91 55 71",
        "Card  27: 98 20 95 96 82 31 83 30 39 40 | 70 53 57 20 13 83 79 91 30 48 29 16 37 59 40  4  8 31 39 82 96 95 98 34 49",
        "Card  28:  8 10 89 23 45 70 32 56 52 15 |  8  9 73  7 16 32 70 58  1 29 97 52 10 89 22 12 27 28 99 23 13 19 18 74 68",
        "Card  29: 56 18 96 63 27 26 24 74 95 99 | 26 84 63 27 74 42 18 21 80 66 99 29 48 96 14 47 46 97 31 56  5 95 52 65 71",
        "Card  30: 81 43 25 69 34 96 27 49 12 39 | 16 63  4 31 27 72 73 15 20 45 95 12 83 87 49 62 43 46 35 22 78 33 47  1 57",
        "Card  31: 22 11 18 71  7 68 74 36 45 14 | 43 50 37 28 23 93 41 27 21 47  3 25 60  2 79 29 68 11 85 22 77 32 18 12 66",
        "Card  32: 90 15  8 74 72 98 36 97 78 21 | 42 76 15 53 79 33 13 83 22 90 96 64 50 18 57 41  8 43 28  6 98 54 14  1 58",
        "Card  33: 73 30 99  5 20 25 62 59 22 96 | 42 24 16 37 23 20 28 50 48 25 14 26 15 86 66 68  3 62 53 99  8 49 22 38 74",
        "Card  34: 91 62 54 39 59 44 20 26 99 65 | 54 26 14 25 11 30 32 18 29 24 66 68 16 65 19  3  5 57 62 35 95 93 20 23 76",
        "Card  35: 63 42 17 45 58 43 27 28 32 44 | 49 18 58 44 83 81 41 40 64 65 72 62  4 15 76 90 92 45 11 16 27 34 93 47 10",
        "Card  36: 43 99 88 69 60 22 48 64  8 58 |  2 22 86  4 81 50 92  6 96 13 73 12 24 34 88 46 49 53 44 18 93 79 37 38 75",
        "Card  37: 66 23 99 95 81 17 83 72  5 86 |  8 78  4 19 98 35 76  1 28 14 86 80  9 54 16  6 40 63 45 26 77 83 11 18 84",
        "Card  38: 45 48 19 78 20 11 29 40  3 82 | 96 20 32 65 57 84 21 74 97 30 37 64 56 42 63  6  1 44 90 22 47  8 73  4 83",
        "Card  39: 84 42 16 77 92 51 26 90 91 85 | 82 69 31 61 59 80 54 71 50 63 12 19 47 56  6 70 95 44 33 97 99 32 20  4 88",
        "Card  40: 96 70 93 79 65 32  7 28 64 38 | 99 35  9 18 28 25 23 42  2 68  7 77 26 89 73 70 93 32  1 40 66 31 54 33 85",
        "Card  41: 92 26 47  4 65 91 82 34 37 96 | 60 87 78 82 55 47 32 36 37 66 65 92 16 52 91 34 50 95 14 17  4 72 99 26 96",
        "Card  42: 33 55 70  4 52 93  6 91 53 84 | 93 53 67 33 72 14 70 44 91 21  1 43 85 56 99 50 55 75  6  4 29 84 52 30 11",
        "Card  43: 12 58 93 91 36 51  9 71 86 35 | 22 20  5 55 45 37 86 23 68 78 96 99 15 63 44 81 72  1 87 19 17 84 13 47  7",
        "Card  44: 69 47 83 29 18 54 34 23 73 15 | 76 73 82 11 23 83 69 35 54 53 34 65 47 41 87 97 15 29 36 60 45 98 68 94 18",
        "Card  45: 10 97 61 73 21 44 67 96 86 32 | 96 73 93 92 83 77 57  7 30 75 21 87 50 60 90 66 35 46 67 38 82 99 63 53 49",
        "Card  46: 75 23 47 59 66 57 87 52 76 46 | 70 77 58 22 47 25 46 88 59 81 89 73 36 66  3 87 23 91  9 52 75 69 76 57  2",
        "Card  47: 93  8  5 76 98 15 17 80 47 88 | 69  7 76 41 79 93 90  2 63 18 36 11 14 87 75 98 64 15 88 31 72  9 40  5 47",
        "Card  48: 70 72 51 79 52 57  8 54 74 93 | 16 51 42 56 79 64 33 60 57 11 47 74 55 17 32  8 91 53 49 29 78 71 52 69 24",
        "Card  49:  5 42 19 48 29 56 43 57 17 84 | 97 84 40  4  9 35 43 19 10 77 71 53  5 83 29 57 28 66 12 62 48 17 14 42 51",
        "Card  50: 23  7  4 93 43 76 32 77 78 46 | 97 30 59 98 21  4 87 70 34 67 40 18 16 11  8 27 47 41 85 62 72 61  3 12 99",
        "Card  51: 79 36 27 10 98 51 70 86 39 31 | 76 25 97 74 38 55  9 27 13 86 89 34 62 36 94 91 10 47 98 56 21  7  2 49 51",
        "Card  52: 54 87 88  2 77 32  7  6 53 14 | 26 22 79  7 64  6 86 31 14 16 67 87 70 74 25  3 77  5 32 28 97 75 61 89  2",
        "Card  53: 77 85 68 86 98 26 79 32 78 91 | 58 86 57 95 85 91 28 44 81 55 68 75 79 98 32 77 31 37 10 27 80 78 74 62 26",
        "Card  54:  1 50 68 38  8 17 83 32 70 89 | 28  8 11 86 56  3 18 76 66 63 34 52 77 83 68 44 24 71  1 89 87 70 32 41 48",
        "Card  55: 20 91 86 23 37  8  9 50 46 19 | 25 96 76 37 20 70 53 17 59 50  8 78 98 35 65 22 91 23 46  9 13 86 82 79 26",
        "Card  56: 76 16 61 72 66 24  5  2 37 96 | 21 94 17 61 76  2 72 88 13  5 84 27 19 81 80 28 47 52  3 24 58 98 71 29 92",
        "Card  57: 50 98 45 73 72 43 70 64 75 25 | 86 27  7 44 83  1 75 77 12 69 52 57 91 25 45 72 38 50 87 54 36 16 84 98 94",
        "Card  58: 50 29 12 67 27 89 31 35 72 76 | 87 20 90 92 35 44 86 91  1 89 64 72 12 14 13 79 19 40  7 76 18 46  6 84 16",
        "Card  59: 92 49 23 30 41 84 75 78 21 90 | 39 44  6 93 50 16 84  1 57 71 55 68 45 46 65 94 38 31 37 85 22  8 77 17 48",
        "Card  60: 45 35 40 31 19 77 49  9 66 93 | 34 83 67 17 62 72 21 68 78 15  7 16 41 91 65 76 79 49 96 32 88  1 11 42 28",
        "Card  61: 67 53 97 70 86 24 81 95 84  7 | 38 63 39 94 22 19 57 44 89 41 85 68 29 10 18 23 14 93 74 30 65 56 54 79 59",
        "Card  62: 63 71 72 82  8 43 13 66 12 84 | 41 36 11  7  9 64 70 78 73 20 99 34 86 25 33 51 52 23 16 35 60 15 84  5  6",
        "Card  63: 29 88 90 76 39 74 12 54  3 79 | 46 87 32 81 19 47 34 17  8 84  5 83  2 71 89 23 75 15 76 24 67  4 59 52 64",
        "Card  64: 96 13 53 94 74 60 81 42 18 28 | 64  1 44 73 91 19 57 31 97 75 89  4 20 71 25 26 65 14 93 70 21 68 61 59 30",
        "Card  65: 90 72 95 67 12 44 52 51 50 57 | 67 96 90  4 26 51 87 53 44 23 72 57 70 25 52 93 95 30 11 84 46 50 78 48 12",
        "Card  66: 41 46 30 49 23 90 63 54 67 47 |  8  5 22 89 55 41 14 79 86 87  7 33 16 85 67 48 42 56 13 96 30 19 84 26 58",
        "Card  67: 30 17 47 87 65  7 18 70 15 76 | 37 32 99 86  5 13 74 83 48 84 77 36 39 78 52 56 63 38 80 45 16 50 57 31 24",
        "Card  68: 39 22 96 79  8 28 60 27 14 46 | 57 19 60 14 72  7 95 46 33 40 82 92 79  8 77 39 22 28 93 52 96 21 97 69 27",
        "Card  69: 45 89 41 95 31 97 68 62 96  5 | 68 65 89 71 83 62 16 23 95  5 31 73 81 86 28 32 41 38 58  2 97 61 53 44 96",
        "Card  70: 76 75 47 32 24 96  3 53 86 15 | 93 31 86  3 67 17 41 24  8 33 75 76 15 40 37 96 64 38  9 47 34 53 42 48 19",
        "Card  71: 71 16 86 20 27 62 65 77 55 83 | 43  6 62 86 83 16 77 75 94 23 19 68 20 96 89 32 76 40 34  1 67 65 53 88 50",
        "Card  72: 59 98 93 94 15 23 73 90 68 18 | 40 15 82 35 98 72 65 50 96 59 24 93 79 32 21 73  2 41 31 44 92 20 28 22 39",
        "Card  73: 92 60 76 81 17 26 25 14 35  8 | 96 72 31 61 25  6 97 55 54 53 95 24 40 13 64 57 50  5  9 59  7 37 30 49 94",
        "Card  74: 62  4 87 35 51 15 99 67 23  5 | 12 61 31 48  2 86 10 14  7 36 50 82 25 75  6 28 58 84 73 49 55 88 18 52 72",
        "Card  75: 96 37 41 11 18 82 39 26 15 54 | 44 36 70  6 50 17 93 22 87  2 26 74 30 33 86  3 56 46 61 25 88 21 77 13 16",
        "Card  76:  9 68 22 42 60 70 16 69 65 93 |  5 96 54 15 82 79 62  8 78 94 11 28 74 75 40 45 56 63 41 95 51 70 99  9 72",
        "Card  77: 69 17 32 84 91 12  6 97 15 14 | 18 66 85 81 80 93 62 96 32 74 17 79 52 45 25 86 82  6 19 30 78 91 67 64 87",
        "Card  78: 93 45 52 86 96 47 56 61 77 58 | 40 41 85 88 55  2 97 46 92 74 68 95 96 20 80 33 93 18  4  1 48 86  5 21 28",
        "Card  79: 69  5 98 49 12 38 80 70  7 95 | 74 78 60 84 99 75 40 86 11 20 44 87  1 57 73 18 36 66 64 35 63  9 59 43 23",
        "Card  80: 32 70 81 23 72 90 58 50 27  1 | 18 72 46  5 85 68 16 83 62 51 98 42 49 14  2 78 63 45 21 82 97 96 56 19 36",
        "Card  81: 36 44 11 30 19  4 15 78 70 45 | 52 95 98 61 20 83 74 32 92  1 82  7 64 86 48 42 99 66 94 47 63 24 62 84 79",
        "Card  82: 44 18 12 63 34 42 41 77 88 21 | 15 88  7 27 55 41  5 44 79 69 68  8 14 18 47 13 38 50 91 70 33 21 62 34 42",
        "Card  83:  2 51 96  3 50 36 45 63 33 12 | 12 51 45 80 35 11 17 16 40 91 96 60 33  4  2 50 84  3 13 20 25 36 73 63 49",
        "Card  84: 65  8 70 96 38 80 57 73 45 44 |  7 65 29 81 56 14 17 26 68 13  5 78 19 41 27 45 72 90  8 77 83 50 87 62 66",
        "Card  85: 66 32 41 87  8 61 95 11 22 13 | 68 42 55  8 41 85 61 66 43 78 96 13 22 98 94  5 52 32 57 17 37 87 11  4 95",
        "Card  86: 66 44 86 73 35 22 39 36 85 10 | 35 27 11 23 66 83 73 36 42 34 20 40 44 19 48 97 85 86 95 14 39 22 57 56 69",
        "Card  87: 53 70 66 78  1 59 16 96 36  3 | 96 59 39  3 36 67 84 27 78 30 16 53 28 89 71 33 55 20 66  1 70  5 13 11 90",
        "Card  88: 90 35  5  6 52 26 58 44 87 61 | 87 44 22 26 74 78 14 76 58 52 70 19 55 66 61 79  5 90 72 35  4 84  6 86 29",
        "Card  89: 42 45 49 34 75 86 63 57 97 73 | 97 63 95 61 73 42 88 98 32 60 57 86 56 49 34 41 16 26  2  5 75 90 33 40 45",
        "Card  90: 92 59 65  2 93 30 17 13  9  4 | 70 90 74  9 75 17  4 85 13 91 56 42 95 77 59 57  8 67 79 64 94 38 60 53 30",
        "Card  91: 83 82  1 65 54 60 35 27 19  4 | 83 79 34 12 17 64 88 74 29 55 13 50 69 39 56 77 93 98 33  6 20 37 24 54 96",
        "Card  92: 34 37 12 73 11 39 97 41 91 80 | 37 92 13 31 70 97 19  2 34 80 12 53 63 91 11 27  3 62 38 23 47 73 52 86 56",
        "Card  93: 81 26 55 67 56 22 77 80 34 46 | 67 26 82 55 46 77 16 56 75 22 95 94 37 81 64 52 31 30  6 84 69 80 17 68  4",
        "Card  94: 36  5 45 93 77 51 85 57 59 43 | 63 93 41 22 71 77 47 87 58 42  4 35 53 46  3 26 83 74 10 76 78 43 73 28 94",
        "Card  95: 25 70 47 26 42 75 27 83 53 99 | 71 81 25 24 12 53 57 68 15 82 38 40 84 89 99 75  5 42 74 55 83 43 62 85 86",
        "Card  96: 46 20 80 16  7 70 49 74 41 56 | 80 49 42 94 72 11 89 70  4 41  3 73 97 74 26 65 43 29 35 82 91 33 19 79 56",
        "Card  97: 48 45 99  5 82 23 43 86 93 78 | 81 83 99 56 91 82 67 93  4 45 34 72  9 85 64 79 78 62 84 25 87 21 80 18  5",
        "Card  98: 48  3 41 78 34 56 82 30 83 59 | 20  5 93 44 57 74 89 60 70  8 72 68 10 95 64 53 31  1 46 14 66  4 96 47 45",
        "Card  99: 77 18 16 94 91 76 32 42 17 34 | 19 63 88 81 22  8 10 74 24 30 21 38 25  9 77 47  5 20 57 72 61 71 80 93 69",
        "Card 100: 34  7 62 10 91  9 44 69  6  2 | 94 57 13 51 21 32 78 97 66 36 15 26  5 29 83 92 25 73 64 65 67 45  1 82 87",
        "Card 101: 18 84 55 74 94 65 40 45 34 60 | 28  6 35 71  8 62 21 67 92 48 86 16  1 73 99 91 37 95 93 53 77 20  5 42 75",
        "Card 102: 83 11 63 21 70 73 13 84 76 50 | 95 44 62  3 14 80 64 34 68 89 22 82 91 67 32 75 39 49  2 63 21 72 77 99 84",
        "Card 103: 39 85 86 55 45 59 87 83 54 92 | 60 31 83 74 23 91 72 32 15 21 27 53 63  6 96 55 43 17 36 37 97  7  1 84 49",
        "Card 104: 54 29 79 91 17 15 53 37 84 20 | 14 98 70 25  2 58 59 35  6 48 22 26 38 27 88 93 41 71 31 28 79 63 83 11 97",
        "Card 105: 29  5 94 92 10 95 48 36 38 75 | 18 55 64 80 34 88 67 53 47 65 14 91 50  2 99 23 39 93  9 42 25 21 41 66 73",
        "Card 106: 38 33 57 27 84 29 80 22 92 89 |  1 54 18 58 32 34 61 45  7 72 78 77 60 46 16 56 85 84 99 30 79 11  5 66 55",
        "Card 107: 11 38 98 41 85 16 15 21 27 76 | 95 82 15 62 70 74 96 75 84 35 52 39 23 10 27  7 65 45 19 80 61 79 29 17  6",
        "Card 108:  6 66 51 90  5 37 46 57 47 20 | 66 39 62 74 63 47 10 37 88 93  8 45 21 51 58 46 72 20 22 44 79 57 54  5  6",
        "Card 109: 73 40 44 37 52 15 19 46 92 30 | 66 69 70 25 87 30 80 67 19 15 55 37 40 44 46 12 61 64 57 14  6 52 38 92 77",
        "Card 110: 40 49 60 87 31 57 37  4 58 22 | 42 82 17 74 71 95 39 40 30 16 65 46 50  7 15 37  4 53 90 11  1 49 60 79 29",
        "Card 111: 29 58 30 10  4 28 84 64 44 69 | 29 86 10 28 34 69 64 91 84 16 19 36 60 97 37 38 58  4 88  9 39 67 77 79 94",
        "Card 112: 80 54 84 46 42 22 65 52 28  5 | 13 66 19 11 24 96 92 73 95 53 43 40 30 68 88 31 98 81 59 51  8 74 58 12 27",
        "Card 113: 89 32 38 43 93 30 70 58 69 91 | 17 87 46 56 69 77 76 32 60 80  6 13 26 86 55 53 54  2 61 94 67 97  7 48 51",
        "Card 114: 23 92 69 14 29  3 42 24 35 53 | 42 26  8 93 89 91 97 23 58 20 95  2 43 82 28 57 55 73 92 44 87  3 74 35 46",
        "Card 115: 73 44 70 63 13 30 31 56 45 82 | 19 83  6 32 59 26 81 95 48 77 97 28  4 72 38 86 14 51 17 80 49 84 57 94 20",
        "Card 116: 61 97 41 65 58 62 48 39 56 27 | 85  8 71 87 37 23 36 45 96 77 42 70 99 74  1 28 91 80 14 24 86 65 64 93 19",
        "Card 117: 56 82 11 19 55 54 14  3 41 26 | 22 47 89 98 87 88 97 90  5 42 14 10 51 56 30 43 86 84 17 73  6 71 61 13 82",
        "Card 118: 58 34 66 92 88 42 69 63 53 10 | 94  3 26 66 96 17 43 74 85 50 97 99 87  5 46 18  1  9 84 81 70 72 14 13 42",
        "Card 119: 82 23  1 43 17 51 42  5 16 39 | 18 72 53 35 93 75 19 49 79 34 33 13 32 39 68 90 63 77 45 96 21 54 31 91 69",
        "Card 120:  4 28 61 93 76 64 78 23 82 97 | 15 49 65 40 81 38 24 71 53 16 36 89 70 45 54 66 35 77 99 13 86 43 73 44 50",
        "Card 121: 99 29  5 72 90 74 56 10 27 43 | 77 90 75 15 33 79 55 62 40 99  5 78 10 56 43 39 29 30 27 51 57 74 98 66 72",
        "Card 122: 41 74 66 90 49 31 64 32 65 60 | 45 81 14  5 92 56 46 74 34 28 49 41  7 32 54 71 76 79 61 64 27 66 25 97 57",
        "Card 123: 24 62  8 51 61 39 13 17 97 30 | 94 61 41 34 98 39 62 79 14 13 36 93  4 24 63 12  8 90 17 45 97 51  9 30 84",
        "Card 124: 91  1 80 58 45 20 76 68 25 95 | 58 38 51 23 24 53  1 45 91 68 85  4 33 20 15 66 96 25 95 74 80 29 97 76 47",
        "Card 125: 17  6 89 81 64 15 47  7 33 78 | 64  9 15 68 62 45 88 17 30 96  7 50 28 33  6 55 46 47  2 57 81 89  4 78 84",
        "Card 126: 29 72 50 54  2 98 77 83 80 68 | 75 22  5 19 36 57 13 30  1 60 41 64  4 32 27 48 52 16 88 85 95  7 31 94 44",
        "Card 127: 55 75 82 33 24 21 76 72 59 61 |  2 13 95  5 53 48 40 38 32 19 12 37 93 31 34 52 83 47 94 22 10 74 41 63 62",
        "Card 128: 13 18 44 37 92 24 58 81 69 17 | 24 58 37 49 13 83 42 88 70 17 96 84 25 19 71 39 92  9 94 69 81 18 44 36 46",
        "Card 129: 28 90 59  5 91 26 66 94 27 69 | 64  9 90 76 71 98 57 15 94 72  7 69 56 19 25 83 27 68 92 95 28  5 26 91 59",
        "Card 130: 80 34 99 65 79 44 83 41 38 69 |  3 50  1 33 82 79 55  9 81 40 18 68 56 22 45 65 57 93 23 35 92 58 72 25 15",
        "Card 131:  9 22 82 86 83 77 41 26 74 66 | 24  9 27 10 94 60 23  6 18 25 87 41 43 83  5 28 54 55 53 26 45 59 91 16 66",
        "Card 132: 86 76 26 67 20 28 53 16 72 70 | 87  6 22 39 55 46 26 86 45 29 91 69  2  1 67 89  7 49 80 61 47 18 16 95 20",
        "Card 133:  3 43 42 13 72 94 75 46 48 56 | 39 36 35 69 90 25 99  1  3 30 71 12 47 48 70 73 41  4 18 64 19 44  5 33 74",
        "Card 134:  7 45 65 35 89 93 14 30 82 48 |  2 30 65 47 79 93 22 11 76 39 82 48 91 60 97  9 95 10 21 35 32 61  7 45 89",
        "Card 135: 68 53 78 75 35 27 92 84 81 57 | 55 96  9  5 79 85 67 60  8 59 71 29 89 34 58 17 93 23 53 75 18  4 51 94 97",
        "Card 136: 41 18 27 96 65 47  7 29 23  2 |  2 77 17 98  4 27 96 57 24 41 53 18 29 21 83 88 28 69  7 68 65 44 25 56 91",
        "Card 137: 60  9 97 72 34 66  6 91 69  1 |  3 72 42 60 94 93 41 76 56 82  2 74 85 54 86 83 14 64 17  4 99 52 79 92 36",
        "Card 138: 52  2 87 77 91 24 21 61 26 96 | 36 17 45 42 63 24 99 43 69 89 49  9 88 30 51 41 94 11 37 75  8 64  2 95 57",
        "Card 139: 46 50 27 34 13 57 19 68 89 96 | 34 56 51 21 49 67 52 53 85 60 31 43 82 66 10 48 70 20 97 76 54 42 25 38 57",
        "Card 140: 84 88 52 17 57 27 11 13 18 40 | 65 52 37 59 72 26 32 71 74 42 49 80 11 93 10 70 63 46 87 54  3 68 92 30 67",
        "Card 141: 34 74 39 30 37 43 97 92 77 89 | 85 46 81 71  8 78 62  1 10 29 58 79 45 31 14 52 35 54 67 55 77 60  5 95  9",
        "Card 142: 47 10 53 58  8 45 36 97 72 98 | 14 82 42 75 28 67 40 16 88  1 18 71 77 98 73 74 35 50 22 83 23 56 96 65  3",
        "Card 143: 33 49 16 67 17 13 74 85 20 26 | 54 56 41 91 16 70 36 45  2 93 21 72 22 83 25 71 10 99 75 59 66 69 61 73 23",
        "Card 144:  4 75 55 42 78 23 33 16 85 64 | 97 44 47  2 70  7 37 83 71 18 22 99 76 96 82 31  6  9 27 41 30 72 56  3 52",
        "Card 145: 41 56 62 45 50 63 17 70 34 24 | 10 71 34 37 17 57  5 33 80 45 32 74  1 46 43 91 38 76 26  3 87 72 65  7 89",
        "Card 146: 45 49 22 73 86  8 34 70 99 84 | 73 78 61 57  8 70 74 20 84 86 82 79 36 22 15 45 34 35 99 96 37 63 26 49 98",
        "Card 147: 70 46  2 81  1 97 55 75 30 78 | 72 75 52 97 60 13  1 30 55 81 66 70  2 78 77 34 84 28 65 57 46 49 43 14 67",
        "Card 148: 94 59 20 34 84 17 28 60 66 73 | 22 51 87 78 36  1 72  6 56 40 74  9 15 24 20  8 64 80 27 85 76 33 13 55 37",
        "Card 149: 92 62 45 59 17 47 65  7 55  5 | 35  8 95 87 36 30 17 19 99 91 82 74 63  9 22 62 89 60 77 66 34 29 90 37 52",
        "Card 150: 75 98 16 79 58 64 74 85 14 80 | 55 74 87 64 39  4 89 52 58 85 88 38 75 14 44 80 25 16 41 79 98 45 66 57 12",
        "Card 151: 24 37 66 84 38 91 94 45 57 71 | 74 89 70 94 90 69 16 24 45 71 27 97 95  2 87 19 91 63 38 22 37 84 32 57 66",
        "Card 152: 56 75 66 32 22 68 60  9 45 21 | 61 63 23 81 56 10 12 51 50 78 57 35 42 99 15 60 43  7 17 39 68 96 71 55 88",
        "Card 153: 96 27  2 50 75 95 48 54 78 47 |  1 14 16  2 47 48 90 62 94 95 59 72 27 30 50 11 54 33 53 78 75 36 86 96 79",
        "Card 154: 23 95 57 63  3  2 27 38 46 60 | 39 57 20 94  8 43 11 82 93 68 80 74 56 67 55 22 88 41 60 28 53 90 31 21 35",
        "Card 155: 93 36 70 72 99 30 67 41 49 61 | 28 46 99 54 86 55 33 93 30 37 50 70 71 29 84 36 49 91 67 43 41 85 75 61 95",
        "Card 156: 46 98 65 18 69 11 81 45 17 47 |  3  1 13 78  2 87 98 28 46 69 34 31 35 44 86 95 70 22 83 51 11 93  4 75 82",
        "Card 157: 36 17 92 60 87  1 32  8 64 59 | 42 87  1 58 44 53 78 74 29 92  3 18 96 64 17 63 40 67 32 38 36 90 54 59 11",
        "Card 158: 19 77 89  1 84  4 68 74 90 37 | 53 66 50 54 19 74 39 27  8 98 67 91 40 48 57 99 78 18 87 62 31 25  3 24 29",
        "Card 159: 23 32 20 56 64 28 66 12 40 22 | 89 94 52 11 82 34 80 91 70 12 42 69 13 29 10 62 71 46 18  9  6  2 88 72  8",
        "Card 160: 69 95 19 80 12 26 15 35 96 44 | 70 34 56 80 59 35 25 95  3 52 86 98 38 89 66 69 83 29 26 21 92 99 22  8 77",
        "Card 161: 31 38 71  5 63 22 56 89 21 73 | 31 68 51 73 70 89 50  7 22 91 34  4  5 46 87 45 15 62 28 85 67 29 58 11  1",
        "Card 162: 46 96 53 48 64 25 98 65 92 36 | 12 22 32 41 71 58  8 79 86 65  5 73 85 36 24 77 20 67  1 83 10 13  2  6  4",
        "Card 163: 35 24 12 28 98 13 72 20 32 44 | 62 78 22 15 16 25 92 72 71 48 50 51 89 83  5 29 34 53 41 49 26 23 82 88 17",
        "Card 164: 98 87 85 89 22 84 21 53 27  7 |  6 15  3 42 30 36 45 65 16 54 78 35 81 50 29 73 70 86 18 96 82 23 51 63 46",
        "Card 165: 21 25 57 72 94 16 81 69 37 24 | 93 31 45 75 49 73 94 92 53 79  2  8 82 61 10  7  5 11 60 80 58 64 25  3 48",
        "Card 166: 78 47 64 51 53  2 67 80 62  7 | 19 91  6 31 55 33  5 14 83 57 99 90 63  8 93 86  1  4 20 11 95 37 75 52 94",
        "Card 167: 21 17 73 35 85 23 80 42 76 64 | 82 96 14 29 81 41 66 52 45 89 28 48 68 54 47 95 71 67 69 30 33 20 51 10 26",
        "Card 168: 28 63 87 77 91 67 74 23 52 35 | 66 81 96 16 95 15 94 42 14 73 39 43 45 76 65 64 85 59 53 26 98 19 80 93 40",
        "Card 169: 83 32 93 81 39 36 52 51 40 78 | 78 50 83 36 10 40 27  9  4 25 75 84 41 91 92 58 12 88 57 39  7 29 74 86 82",
        "Card 170: 99 67 50 63 84 33 46 80 43 57 | 63 26 80  6 86 14 29 65 40 25 35 18 89 33 73  4 51 54 23 12 84 62 36 39 60",
        "Card 171: 33  9 66  6 53 24 48 90 74 11 | 94 44 88 98 46 36 37 80 92 83 90 96 49 47 11 50 16 17 91 39  8 27 42 86 59",
        "Card 172: 48 41 58 49 93 35 55 52 53 57 | 50 67 31 21 20 87 35 18  1 33 19 43 57 41 28 72 26  2 90 16 37 69 56 97 46",
        "Card 173: 74 50 62 84  4 28 27 82 40 78 | 40 50 55 74 17 15 94 78 38 79 27 28 34 21 62 43 84 61 20 68 91 82 75 86  4",
        "Card 174: 85 12 52 64 29 97 77 22 13 15 | 60 83 74 79 56 27 36 57 85 76 20 81 43 49 15  5 80 45 63 47 14 97 59 30 91",
        "Card 175: 96 35  6 22 30 45 39 19 26 18 | 17  2 34  3 95 11 52 42 91 41 54 13 72 15 24 78 94 33 58  9 83 57 88 76 64",
        "Card 176: 38 60 24 42 53 34 73 77 37 33 | 32 53 99 34 87  3 14 46 76 22 63 16 59 23  9 73 54 89 70 37 69 83 42 64 96",
        "Card 177: 32 74 75 96 67 31 97 93 34 42 | 37 95 23 52 98 99 84 78 48 27 82 24 14 62 38 61 76 16 50 89 92 65 80 13 51",
        "Card 178: 82 24 44 85 29 76 87 33 20 25 |  6 98  8 36 80 62 66 27 13  1 26 93 68 73  7 48 79 15 49 32 72 77 41 78 42",
        "Card 179: 10 74 26 90 41 76  5 19 59 43 | 23 18 38 27 62  9 70 74 39 91 96 33 60 61 17 53 59 28 83 87 88 42 99  6 20",
        "Card 180: 70 23 63 77 57 31 74 36 13 21 | 45 18  5  7 66 55 14 58 90 89 64 50 30 69 97 54 92 98 85 29 91 37 28 75 11",
        "Card 181: 10 94 33  8 92  5 89 34 64 22 | 96 91 46 56 52 40 13 84 20 49 63 90 98 72  3 57 61 82  4 85 95 32 74  7 36",
        "Card 182: 92 61 58 20 65 36 45  3 95 11 | 77 52 14 17 83 50 51 55 12 56 95 98 24 46 49 88 35 61 71 87 47 69 11 81 78",
        "Card 183: 80 93 28 86 61 95 67 25 37 83 | 31 21 72 10 84 20 40 17 98 63 92 19  3 47 30 75 22 24 15  4 78 97 33  9 61",
        "Card 184: 10 86 78 37 67 38 35 44 14 16 | 79 93 96 68 58 92 11 19 77 48 26  1 23 46 20 42 64 39 47 25 45 21 53  3 18",
        "Card 185: 21 89 81 37 84 78 83  8 59 47 | 30 75  7  9 98 95 26 55 51 57  1 28 40 86  6 82 19 45 23 94 73 34 42  3 43",
        "Card 186: 49 48 54 21 94 62  7 70 69  8 |  4 66  3 25 73 41 24 53 76 55 33 60 97 92 48 15  1  7 52 14 46 36 85 77  2",
        "Card 187: 24 30  4 33 64 68 29 17 83 58 | 71 68 42 33 58 17 65 23  3 53  4  5 80 90 14 61 69 29 66 30 83 32 59 11 24",
        "Card 188: 63 79 12 95 23 97 11 13 27 60 | 75 23 37 63 76 58 87 82  4 28 19 13  7 10 72 34 40 41 97 98 91 95 60 71 12",
        "Card 189: 78 56 54 65 85 17 89 99 16 81 | 12 48 76 92 23 34 61  2 27 53 22  7 50 84 59 72 13 21 36 31 85 39 87 64 89",
        "Card 190: 85  2 47 27 69 39 76 51 23 98 | 75 26 83 39 15 28 18 76 63 51 87 98 77 23 10 30 27 85 57 36  2 69  5 47 17",
        "Card 191: 23 98 22 11 72 38 87 18 77 60 |  7 97 64 40 27 71 79 61 38 13  1 46 98 10  4 44 12 73 30 75 23  3 96 99 48",
        "Card 192: 12 34 41 10 48 29 17 72 75 24 | 37 95 30 32 17 26 68 28 38 24 29 72 36 62 34 12 43 75 10 41 84 15 77 48 57",
        "Card 193: 61  6 10 99 38  4 96 42 43 20 | 43 14 71 41  3 59 42 20 13 62 84 37 48 23 46 61 92  4 11 99 38 33 74 32  6",
        "Card 194: 26 84 28 93 89 22 55 76 77 94 | 97 77 41 90 37 87 76 89 64 35  8 79  9 23 86 10 36 22 60 84  2 67 28  1 27",
        "Card 195: 88 86 74 35 43 14 48 97 13 61 | 21 35 70  5 92 15 60  4 10 42 88 22 74 56 18 13 72 77 97 43 33 87 14 31 66",
        "Card 196: 75 34 29 68 61 82 78 87 49 21 | 81 52 69 45 67 53 37 83 76 63 10 93 41 43 85 18 47 84 70 16 66 80 29  5 73",
        "Card 197: 82 68 33 60 93 16 55 73 52 84 | 74 14  2 13 96 64 91 59 43 61 54 15 53 76 66 50 48 22 79 99 73 26 30 83  5",
        "Card 198:  4 20 65  8 84 37 63 77 43 61 | 43 28 16 14 71 42 93 35 81 23 46 76 70 25 30 31 26  7 21 37 65 95 19  9 33",
        "Card 199:  8 16 77 25 24 89 47 74 85 38 | 22 44 73 87 18 41 96 14 46 52 36 27 33 23 48 82 39 75 86  7 90 81 59 88 98",
        "Card 200: 63  8 58 53 65 99 88 85 70 93 | 97 88  3  5 22 76 24 63 89 68 15 25 57 50 33 27 35 83 45 99 65 52 85 37 32",
        "Card 201: 80 14 49 45 60 87 30 15 84 16 | 69 80 33 96 60 21 26 57  8 36 87 70  3 92 24 45 83 58 14 35 39  1 19 23 15",
        "Card 202: 60 32 46 11 35 62 90 24 31 73 | 61 89 27 98 68 88 59 13 94 29 45 82 21 34  3 64 69 57 63 75 96  6 72 56 25",
        "Card 203: 31 66 21 63 36 91 49 68 65 64 | 18 54 23 99 22 97 65 75 16  2 49 57 93 84 28 48 35 98 47 20 25 60 52  5  4",
        "Card 204: 91 64 59 76 89 34 36 35 68 44 | 74 75 57 55 11 83 66 43 97 17 88  9 28 42 31 81  6 77 95 18 52 85 99 34 16",
        "Card 205: 66 58 78 27 80 97 31 75 12 69 | 77 73 68 40 98 51 37 82 87 44 54 65 81 41 67 79 66 99 95 85  3 13 24 29 58",
        "Card 206: 22 24 13 27  2 41 64 38 43 82 | 79 29 63 58 17 71 91 47 80 59 14 98 50 53 11 94 65  8 66 62 18 36 60 72 85",
        "Card 207: 52 14 61 69 16 53  1 34  9 77 | 21 59 75 39 60 40 38 74 95 97 46 80 19 28 64 66 57 37 41 90  7 62 32 58  4"
)

class Card(id: Int, winningNumbers: List<Int>, pickedNumbers: List<Int>, numberOfCopies: Int = 0) {
    val id = id
    val winningNumbers = winningNumbers
    val pickedNumbers = pickedNumbers
    var numberOfCopies = numberOfCopies

    fun getScore(): Int {
        var includedNumbers = pickedNumbers.filter { winningNumbers.indexOf(it) >= 0 }.size
        return includedNumbers
    }

    fun getScoreWithPower(): Int {
        val score = getScore()
        if (score == 0) return 0

        return 2.0.pow(score - 1).toInt()
    }

    override fun toString(): String {
        return ""
    }
}

fun main() {
    val cards = mutableListOf<Card>()
    for (element in data) {
        val cardElementSplit = element.split(":")
        val id = cardElementSplit[0].replace("\\s+".toRegex(), " ").split(" ")[1].toInt()

        val pickedNumbersWinningNumbersSplit = cardElementSplit[1].split("|")

        val winningNumbers = pickedNumbersWinningNumbersSplit[0].trim().replace("\\s+".toRegex(), " ").split(" ").map { it.toInt() }
        val pickedNumbers = pickedNumbersWinningNumbersSplit[1].trim().replace("\\s+".toRegex(), " ").split(" ").map { it.toInt() }

        cards.add(Card(id, winningNumbers, pickedNumbers))
    }

    println("score (part-1): " + cards.sumOf { it.getScoreWithPower() })

    val initialCardsSize = cards.size
    for (i in 0..<initialCardsSize) {
        val score = cards[i].getScore()
        val instances = 1 + cards[i].numberOfCopies
        var j = i + 1
        var currentScore = 0
        while (currentScore < score*instances) {
            cards[j].numberOfCopies += 1
            currentScore += 1
            j += 1
            if (j == i + score + 1) j = i + 1
        }
    }
    println("score (part-2): " +cards.sumOf { it.numberOfCopies + 1 })
}

main()