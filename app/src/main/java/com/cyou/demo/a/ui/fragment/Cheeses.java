/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cyou.demo.a.ui.fragment;


import com.cyou.demo.R;

import java.util.Random;

public class Cheeses {

    private static final Random RANDOM = new Random();

    public static int getRandomCheeseDrawable() {
        switch (RANDOM.nextInt(5)) {
            default:
            case 0:
                return R.drawable.cheese_1;
            case 1:
                return R.drawable.cheese_2;
            case 2:
                return R.drawable.cheese_3;
            case 3:
                return R.drawable.cheese_4;
            case 4:
                return R.drawable.cheese_5;
        }
    }
        public static String getRandomIcon() {
                return imageUrls[(RANDOM.nextInt(imageUrls.length))];
        }
    public static final String[] sCheeseStrings = {
            "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
            "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale",
            "Aisy Cendre", "Allgauer Emmentaler", "Alverca", "Ambert", "American Cheese",
            "Ami du Chambertin", "Anejo Enchilado", "Anneau du Vic-Bilh", "Anthoriro", "Appenzell",
            "Aragon", "Ardi Gasna", "Ardrahan", "Armenian String", "Aromes au Gene de Marc",
            "Asadero", "Asiago", "Aubisque Pyrenees", "Autun", "Avaxtskyr", "Baby Swiss",
            "Babybel", "Baguette Laonnaise", "Bakers", "Baladi", "Balaton", "Bandal", "Banon",
            "Barry's Bay Cheddar", "Basing", "Basket Cheese", "Bath Cheese", "Bavarian Bergkase",
            "Baylough", "Beaufort", "Beauvoorde", "Beenleigh Blue", "Beer Cheese", "Bel Paese",
            "Bergader", "Bergere Bleue", "Berkswell", "Beyaz Peynir", "Bierkase", "Bishop Kennedy",
            "Blarney", "Bleu d'Auvergne", "Bleu de Gex", "Bleu de Laqueuille",
            "Bleu de Septmoncel", "Bleu Des Causses", "Blue", "Blue Castello", "Blue Rathgore",
            "Blue Vein (Australian)", "Blue Vein Cheeses", "Bocconcini", "Bocconcini (Australian)",
            "Boeren Leidenkaas", "Bonchester", "Bosworth", "Bougon", "Boule Du Roves",
            "Boulette d'Avesnes", "Boursault", "Boursin", "Bouyssou", "Bra", "Braudostur",
            "Breakfast Cheese", "Brebis du Lavort", "Brebis du Lochois", "Brebis du Puyfaucon",
            "Bresse Bleu", "Brick", "Brie", "Brie de Meaux", "Brie de Melun", "Brillat-Savarin",
            "Brin", "Brin d' Amour", "Brin d'Amour", "Brinza (Burduf Brinza)",
            "Briquette de Brebis", "Briquette du Forez", "Broccio", "Broccio Demi-Affine",
            "Brousse du Rove", "Bruder Basil", "Brusselae Kaas (Fromage de Bruxelles)", "Bryndza",
            "Buchette d'Anjou", "Buffalo", "Burgos", "Butte", "Butterkase", "Button (Innes)",
            "Buxton Blue", "Cabecou", "Caboc", "Cabrales", "Cachaille", "Caciocavallo", "Caciotta",
            "Caerphilly", "Cairnsmore", "Calenzana", "Cambazola", "Camembert de Normandie",
            "Canadian Cheddar", "Canestrato", "Cantal", "Caprice des Dieux", "Capricorn Goat",
            "Capriole Banon", "Carre de l'Est", "Casciotta di Urbino", "Cashel Blue", "Castellano",
            "Castelleno", "Castelmagno", "Castelo Branco", "Castigliano", "Cathelain",
            "Celtic Promise", "Cendre d'Olivet", "Cerney", "Chabichou", "Chabichou du Poitou",
            "Chabis de Gatine", "Chaource", "Charolais", "Chaumes", "Cheddar",
            "Cheddar Clothbound", "Cheshire", "Chevres", "Chevrotin des Aravis", "Chontaleno",
            "Civray", "Coeur de Camembert au Calvados", "Coeur de Chevre", "Colby", "Cold Pack",
            "Comte", "Coolea", "Cooleney", "Coquetdale", "Corleggy", "Cornish Pepper",
            "Cotherstone", "Cotija", "Cottage Cheese", "Cottage Cheese (Australian)",
            "Cougar Gold", "Coulommiers", "Coverdale", "Crayeux de Roncq", "Cream Cheese",
            "Cream Havarti", "Crema Agria", "Crema Mexicana", "Creme Fraiche", "Crescenza",
            "Croghan", "Crottin de Chavignol", "Crottin du Chavignol", "Crowdie", "Crowley",
            "Cuajada", "Curd", "Cure Nantais", "Curworthy", "Cwmtawe Pecorino",
            "Cypress Grove Chevre", "Danablu (Danish Blue)", "Danbo", "Danish Fontina",
            "Daralagjazsky", "Dauphin", "Delice des Fiouves", "Denhany Dorset Drum", "Derby",
            "Dessertnyj Belyj", "Devon Blue", "Devon Garland", "Dolcelatte", "Doolin",
            "Doppelrhamstufel", "Dorset Blue Vinney", "Double Gloucester", "Double Worcester",
            "Dreux a la Feuille", "Dry Jack", "Duddleswell", "Dunbarra", "Dunlop", "Dunsyre Blue",
            "Duroblando", "Durrus", "Dutch Mimolette (Commissiekaas)", "Edam", "Edelpilz",
            "Emental Grand Cru", "Emlett", "Emmental", "Epoisses de Bourgogne", "Esbareich",
            "Esrom", "Etorki", "Evansdale Farmhouse Brie", "Evora De L'Alentejo", "Exmoor Blue",
            "Explorateur", "Feta", "Feta (Australian)", "Figue", "Filetta", "Fin-de-Siecle",
            "Finlandia Swiss", "Finn", "Fiore Sardo", "Fleur du Maquis", "Flor de Guia",
            "Flower Marie", "Folded", "Folded cheese with mint", "Fondant de Brebis",
            "Fontainebleau", "Fontal", "Fontina Val d'Aosta", "Formaggio di capra", "Fougerus",
            "Four Herb Gouda", "Fourme d' Ambert", "Fourme de Haute Loire", "Fourme de Montbrison",
            "Fresh Jack", "Fresh Mozzarella", "Fresh Ricotta", "Fresh Truffles", "Fribourgeois",
            "Friesekaas", "Friesian", "Friesla", "Frinault", "Fromage a Raclette", "Fromage Corse",
            "Fromage de Montagne de Savoie", "Fromage Frais", "Fruit Cream Cheese",
            "Frying Cheese", "Fynbo", "Gabriel", "Galette du Paludier", "Galette Lyonnaise",
            "Galloway Goat's Milk Gems", "Gammelost", "Gaperon a l'Ail", "Garrotxa", "Gastanberra",
            "Geitost", "Gippsland Blue", "Gjetost", "Gloucester", "Golden Cross", "Gorgonzola",
            "Gornyaltajski", "Gospel Green", "Gouda", "Goutu", "Gowrie", "Grabetto", "Graddost",
            "Grafton Village Cheddar", "Grana", "Grana Padano", "Grand Vatel",
            "Grataron d' Areches", "Gratte-Paille", "Graviera", "Greuilh", "Greve",
            "Gris de Lille", "Gruyere", "Gubbeen", "Guerbigny", "Halloumi",
            "Halloumy (Australian)", "Haloumi-Style Cheese", "Harbourne Blue", "Havarti",
            "Heidi Gruyere", "Hereford Hop", "Herrgardsost", "Herriot Farmhouse", "Herve",
            "Hipi Iti", "Hubbardston Blue Cow", "Hushallsost", "Iberico", "Idaho Goatster",
            "Idiazabal", "Il Boschetto al Tartufo", "Ile d'Yeu", "Isle of Mull", "Jarlsberg",
            "Jermi Tortes", "Jibneh Arabieh", "Jindi Brie", "Jubilee Blue", "Juustoleipa",
            "Kadchgall", "Kaseri", "Kashta", "Kefalotyri", "Kenafa", "Kernhem", "Kervella Affine",
            "Kikorangi", "King Island Cape Wickham Brie", "King River Gold", "Klosterkaese",
            "Knockalara", "Kugelkase", "L'Aveyronnais", "L'Ecir de l'Aubrac", "La Taupiniere",
            "La Vache Qui Rit", "Laguiole", "Lairobell", "Lajta", "Lanark Blue", "Lancashire",
            "Langres", "Lappi", "Laruns", "Lavistown", "Le Brin", "Le Fium Orbo", "Le Lacandou",
            "Le Roule", "Leafield", "Lebbene", "Leerdammer", "Leicester", "Leyden", "Limburger",
            "Lincolnshire Poacher", "Lingot Saint Bousquet d'Orb", "Liptauer", "Little Rydings",
            "Livarot", "Llanboidy", "Llanglofan Farmhouse", "Loch Arthur Farmhouse",
            "Loddiswell Avondale", "Longhorn", "Lou Palou", "Lou Pevre", "Lyonnais", "Maasdam",
            "Macconais", "Mahoe Aged Gouda", "Mahon", "Malvern", "Mamirolle", "Manchego",
            "Manouri", "Manur", "Marble Cheddar", "Marbled Cheeses", "Maredsous", "Margotin",
            "Maribo", "Maroilles", "Mascares", "Mascarpone", "Mascarpone (Australian)",
            "Mascarpone Torta", "Matocq", "Maytag Blue", "Meira", "Menallack Farmhouse",
            "Menonita", "Meredith Blue", "Mesost", "Metton (Cancoillotte)", "Meyer Vintage Gouda",
            "Mihalic Peynir", "Milleens", "Mimolette", "Mine-Gabhar", "Mini Baby Bells", "Mixte",
            "Molbo", "Monastery Cheeses", "Mondseer", "Mont D'or Lyonnais", "Montasio",
            "Monterey Jack", "Monterey Jack Dry", "Morbier", "Morbier Cru de Montagne",
            "Mothais a la Feuille", "Mozzarella", "Mozzarella (Australian)",
            "Mozzarella di Bufala", "Mozzarella Fresh, in water", "Mozzarella Rolls", "Munster",
            "Murol", "Mycella", "Myzithra", "Naboulsi", "Nantais", "Neufchatel",
            "Neufchatel (Australian)", "Niolo", "Nokkelost", "Northumberland", "Oaxaca",
            "Olde York", "Olivet au Foin", "Olivet Bleu", "Olivet Cendre",
            "Orkney Extra Mature Cheddar", "Orla", "Oschtjepka", "Ossau Fermier", "Ossau-Iraty",
            "Oszczypek", "Oxford Blue", "P'tit Berrichon", "Palet de Babligny", "Paneer", "Panela",
            "Pannerone", "Pant ys Gawn", "Parmesan (Parmigiano)", "Parmigiano Reggiano",
            "Pas de l'Escalette", "Passendale", "Pasteurized Processed", "Pate de Fromage",
            "Patefine Fort", "Pave d'Affinois", "Pave d'Auge", "Pave de Chirac", "Pave du Berry",
            "Pecorino", "Pecorino in Walnut Leaves", "Pecorino Romano", "Peekskill Pyramid",
            "Pelardon des Cevennes", "Pelardon des Corbieres", "Penamellera", "Penbryn",
            "Pencarreg", "Perail de Brebis", "Petit Morin", "Petit Pardou", "Petit-Suisse",
            "Picodon de Chevre", "Picos de Europa", "Piora", "Pithtviers au Foin",
            "Plateau de Herve", "Plymouth Cheese", "Podhalanski", "Poivre d'Ane", "Polkolbin",
            "Pont l'Eveque", "Port Nicholson", "Port-Salut", "Postel", "Pouligny-Saint-Pierre",
            "Pourly", "Prastost", "Pressato", "Prince-Jean", "Processed Cheddar", "Provolone",
            "Provolone (Australian)", "Pyengana Cheddar", "Pyramide", "Quark",
            "Quark (Australian)", "Quartirolo Lombardo", "Quatre-Vents", "Quercy Petit",
            "Queso Blanco", "Queso Blanco con Frutas --Pina y Mango", "Queso de Murcia",
            "Queso del Montsec", "Queso del Tietar", "Queso Fresco", "Queso Fresco (Adobera)",
            "Queso Iberico", "Queso Jalapeno", "Queso Majorero", "Queso Media Luna",
            "Queso Para Frier", "Queso Quesadilla", "Rabacal", "Raclette", "Ragusano", "Raschera",
            "Reblochon", "Red Leicester", "Regal de la Dombes", "Reggianito", "Remedou",
            "Requeson", "Richelieu", "Ricotta", "Ricotta (Australian)", "Ricotta Salata", "Ridder",
            "Rigotte", "Rocamadour", "Rollot", "Romano", "Romans Part Dieu", "Roncal", "Roquefort",
            "Roule", "Rouleau De Beaulieu", "Royalp Tilsit", "Rubens", "Rustinu", "Saaland Pfarr",
            "Saanenkaese", "Saga", "Sage Derby", "Sainte Maure", "Saint-Marcellin",
            "Saint-Nectaire", "Saint-Paulin", "Salers", "Samso", "San Simon", "Sancerre",
            "Sap Sago", "Sardo", "Sardo Egyptian", "Sbrinz", "Scamorza", "Schabzieger", "Schloss",
            "Selles sur Cher", "Selva", "Serat", "Seriously Strong Cheddar", "Serra da Estrela",
            "Sharpam", "Shelburne Cheddar", "Shropshire Blue", "Siraz", "Sirene", "Smoked Gouda",
            "Somerset Brie", "Sonoma Jack", "Sottocenare al Tartufo", "Soumaintrain",
            "Sourire Lozerien", "Spenwood", "Sraffordshire Organic", "St. Agur Blue Cheese",
            "Stilton", "Stinking Bishop", "String", "Sussex Slipcote", "Sveciaost", "Swaledale",
            "Sweet Style Swiss", "Swiss", "Syrian (Armenian String)", "Tala", "Taleggio", "Tamie",
            "Tasmania Highland Chevre Log", "Taupiniere", "Teifi", "Telemea", "Testouri",
            "Tete de Moine", "Tetilla", "Texas Goat Cheese", "Tibet", "Tillamook Cheddar",
            "Tilsit", "Timboon Brie", "Toma", "Tomme Brulee", "Tomme d'Abondance",
            "Tomme de Chevre", "Tomme de Romans", "Tomme de Savoie", "Tomme des Chouans", "Tommes",
            "Torta del Casar", "Toscanello", "Touree de L'Aubier", "Tourmalet",
            "Trappe (Veritable)", "Trois Cornes De Vendee", "Tronchon", "Trou du Cru", "Truffe",
            "Tupi", "Turunmaa", "Tymsboro", "Tyn Grug", "Tyning", "Ubriaco", "Ulloa",
            "Vacherin-Fribourgeois", "Valencay", "Vasterbottenost", "Venaco", "Vendomois",
            "Vieux Corse", "Vignotte", "Vulscombe", "Waimata Farmhouse Blue",
            "Washed Rind Cheese (Australian)", "Waterloo", "Weichkaese", "Wellington",
            "Wensleydale", "White Stilton", "Whitestone Farmhouse", "Wigmore", "Woodside Cabecou",
            "Xanadu", "Xynotyro", "Yarg Cornish", "Yarra Valley Pyramid", "Yorkshire Blue",
            "Zamorano", "Zanetti Grana Padano", "Zanetti Parmigiano Reggiano"
    };
        private final static String[] imageUrls = new String[]{
                "http://img4.duitang.com/uploads/blog/201311/04/20131104193715_NCexN.thumb.jpeg",
                "http://cdn.duitang.com/uploads/blog/201401/07/20140107223310_LH3Uy.thumb.jpeg",
                "http://img5.duitang.com/uploads/item/201405/09/20140509222156_kVexJ.thumb.jpeg",
                "http://img5.duitang.com/uploads/item/201306/14/20130614185903_raNR3.thumb.jpeg",
                "http://img5.duitang.com/uploads/item/201112/11/20111211191621_HU4Bj.thumb.jpg",
                "http://cdn.duitang.com/uploads/item/201408/07/20140807224553_VXaUc.thumb.jpeg",
                "http://img5.duitang.com/uploads/blog/201407/29/20140729105929_GQLwC.thumb.jpeg",
                "http://img4.duitang.com/uploads/blog/201408/04/20140804160432_LnFeB.thumb.jpeg",
                "http://img5.duitang.com/uploads/blog/201408/04/20140804161101_JVJea.thumb.jpeg",
                "http://cdn.duitang.com/uploads/blog/201408/04/20140804093210_FxFNd.thumb.jpeg",
                "http://img5.duitang.com/uploads/blog/201408/04/20140804160314_hRKtv.thumb.jpeg",
                "http://cdn.duitang.com/uploads/item/201408/01/20140801080524_SnGfE.thumb.jpeg",
                "http://img5.duitang.com/uploads/item/201407/23/20140723140958_NSWfE.thumb.jpeg",
                "http://img4.duitang.com/uploads/blog/201407/22/20140722153305_WHejQ.thumb.jpeg",
                "http://img5.duitang.com/uploads/item/201407/21/20140721010148_ZBQwe.thumb.jpeg",
                "http://cdn.duitang.com/uploads/item/201407/23/20140723085122_cmteu.thumb.jpeg",
                "http://img5.duitang.com/uploads/item/201407/23/20140723130620_Z2yJB.thumb.jpeg",
                "http://cdn.duitang.com/uploads/blog/201407/20/20140720204738_NXxLE.thumb.jpeg",
                "http://cdn.duitang.com/uploads/item/201407/20/20140720134916_VGfyh.thumb.jpeg",
                "http://img5.duitang.com/uploads/blog/201407/17/20140717113117_mUssJ.thumb.jpeg",
                "http://img5.duitang.com/uploads/blog/201407/17/20140717121501_wfFEm.thumb.jpeg",
                "http://img5.duitang.com/uploads/blog/201407/17/20140717121431_w4AV8.thumb.jpeg",
                "http://img5.duitang.com/uploads/blog/201407/17/20140717121918_TtJjY.thumb.jpeg",
                "http://img5.duitang.com/uploads/item/201407/11/20140711234806_FNLBQ.thumb.jpeg",
                "http://img5.duitang.com/uploads/item/201407/18/20140718121437_UyiAS.thumb.jpeg",
                "http://img5.duitang.com/uploads/blog/201407/17/20140717114829_RiCXR.thumb.jpeg",
                "http://img4.duitang.com/uploads/blog/201407/17/20140717120301_wGFYL.thumb.jpeg",
                "http://img5.duitang.com/uploads/blog/201405/11/20140511121106_JXS4B.thumb.jpeg",
                "http://img5.duitang.com/uploads/blog/201405/10/20140510094144_kfLji.thumb.jpeg",
                "http://img5.duitang.com/uploads/blog/201405/09/20140509201906_kERjy.thumb.jpeg",
                "http://img5.duitang.com/uploads/blog/201405/08/20140508193226_UaSGB.thumb.jpeg",
                "http://img4.duitang.com/uploads/blog/201405/05/20140505201747_aPNtf.thumb.jpeg",
                "http://img5.duitang.com/uploads/blog/201405/06/20140506104824_jPWQj.thumb.jpeg",
                "http://img5.duitang.com/uploads/blog/201405/05/20140505201105_MkXdn.thumb.jpeg",
                "http://img5.duitang.com/uploads/blog/201405/03/20140503205822_GCzta.thumb.jpeg",
                "http://img5.duitang.com/uploads/blog/201405/03/20140503205535_cCHPB.thumb.jpeg",
                "http://img5.duitang.com/uploads/blog/201405/03/20140503204354_xxSQX.thumb.jpeg",
                "http://img4.duitang.com/uploads/blog/201404/06/20140406191307_GTxFd.thumb.jpeg",
                "http://img4.duitang.com/uploads/blog/201404/06/20140406191247_BG2cU.thumb.jpeg",
                "http://img4.duitang.com/uploads/blog/201404/06/20140406191114_MzYtw.thumb.jpeg",
                "http://img4.duitang.com/uploads/blog/201404/06/20140406191127_fazJA.thumb.jpeg",
                "http://img5.duitang.com/uploads/item/201407/10/20140710081204_vYnCi.thumb.jpeg",
                "http://img5.duitang.com/uploads/item/201407/15/20140715133758_M2Y3J.thumb.jpeg",
                "http://img4.duitang.com/uploads/item/201407/13/20140713190806_TGJHm.thumb.jpeg",
                "http://img4.duitang.com/uploads/blog/201407/05/20140705223413_5r3ze.thumb.jpeg",
                "http://img5.duitang.com/uploads/item/201407/13/20140713012526_tcy5u.thumb.jpeg",
                "http://img5.duitang.com/uploads/blog/201407/13/20140713121424_Gy43T.thumb.jpeg",
                "http://img4.duitang.com/uploads/item/201407/15/20140715033844_tcvrY.thumb.jpeg",
                "http://img5.duitang.com/uploads/item/201407/10/20140710181106_4HHay.thumb.jpeg",
                "http://img5.duitang.com/uploads/blog/201407/06/20140706122850_8PER3.thumb.jpeg",
                "http://img5.duitang.com/uploads/blog/201407/07/20140707192042_8xKXF.thumb.jpeg",
                "http://img5.duitang.com/uploads/item/201407/07/20140707063954_mVa3y.thumb.jpeg",
                "http://img5.duitang.com/uploads/item/201407/08/20140708093733_AFFhc.thumb.jpeg",
                "http://img5.duitang.com/uploads/blog/201407/07/20140707161220_unvzn.thumb.jpeg",
                "http://img4.duitang.com/uploads/blog/201407/07/20140707113856_hBf3R.thumb.jpeg",
                "http://img5.duitang.com/uploads/item/201308/26/20130826090203_NtuYA.thumb.jpeg",
                "http://img4.duitang.com/uploads/item/201407/07/20140707145925_dHeKV.thumb.jpeg",
                "http://img5.duitang.com/uploads/item/201406/25/20140625101534_xiZxN.thumb.jpeg",
                "http://img5.duitang.com/uploads/blog/201406/30/20140630150534_EWUVY.thumb.jpeg",
                "http://img5.duitang.com/uploads/item/201406/25/20140625121626_ZmT5n.thumb.jpeg",
                "http://img4.duitang.com/uploads/blog/201307/31/20130731231806_4yGxV.thumb.jpeg",
                "http://img5.duitang.com/uploads/item/201406/28/20140628122218_fLQyP.thumb.jpeg",
                "http://img5.duitang.com/uploads/blog/201406/26/20140626131831_MrdKP.thumb.jpeg",
                "http://img5.duitang.com/uploads/blog/201406/16/20140616165201_nuKWj.thumb.jpeg",
                "http://img5.duitang.com/uploads/item/201406/25/20140625140308_KP4rn.thumb.jpeg",
                "http://img5.duitang.com/uploads/item/201406/25/20140625121604_2auuA.thumb.jpeg",
                "http://img4.duitang.com/uploads/item/201406/25/20140625131625_LmmLZ.thumb.jpeg",
                "http://img5.duitang.com/uploads/item/201406/25/20140625132851_mPmKY.thumb.jpeg",
                "http://img5.duitang.com/uploads/item/201406/25/20140625133312_ZtmW4.thumb.jpeg",
                "http://img5.duitang.com/uploads/item/201406/25/20140625164858_AuafS.thumb.jpeg",
                "http://img5.duitang.com/uploads/item/201406/24/20140624114145_e4iVw.thumb.jpeg",
                "http://cdn.duitang.com/uploads/item/201406/25/20140625100427_Hkxj5.thumb.jpeg",
                "http://cdn.duitang.com/uploads/blog/201406/25/20140625213455_VHHcL.thumb.jpeg",
                "http://img5.duitang.com/uploads/item/201406/25/20140625132659_UuES4.thumb.jpeg",
                "http://img5.duitang.com/uploads/item/201406/24/20140624020050_zCE4U.thumb.jpeg",
                "http://img5.duitang.com/uploads/item/201406/18/20140618152533_dJjtW.thumb.jpeg",
                "http://cdn.duitang.com/uploads/item/201406/20/20140620075216_twZE4.thumb.jpeg",
                "http://img5.duitang.com/uploads/item/201406/22/20140622162247_Z4WK4.thumb.jpeg",
                "http://cdn.duitang.com/uploads/item/201406/20/20140620075158_TnyKU.thumb.jpeg",
                "http://img5.duitang.com/uploads/blog/201406/18/20140618235506_5QJwc.thumb.jpeg",
                "http://img5.duitang.com/uploads/item/201406/18/20140618152515_AFcLy.thumb.jpeg",
                "http://img5.duitang.com/uploads/item/201406/03/20140603001954_NjKfX.thumb.jpeg",
                "http://cdn.duitang.com/uploads/item/201405/31/20140531232042_4FkHQ.thumb.jpeg",
                "http://img4.duitang.com/uploads/blog/201406/13/20140613002234_LHXcT.thumb.jpeg",
                "http://img4.duitang.com/uploads/item/201405/31/20140531231843_J5Euh.thumb.jpeg",
                "http://img5.duitang.com/uploads/item/201406/11/20140611220941_xBeyi.thumb.jpeg",
                "http://img5.duitang.com/uploads/item/201406/13/20140613114809_yuHRV.thumb.jpeg",
                "http://cdn.duitang.com/uploads/item/201406/13/20140613120109_yL8hk.thumb.jpeg",
                "http://img5.duitang.com/uploads/blog/201406/01/20140601185856_Q5jZr.thumb.jpeg",
                "http://img5.duitang.com/uploads/item/201406/06/20140606004724_GxQHQ.thumb.jpeg",
                "http://img4.duitang.com/uploads/item/201406/08/20140608003809_3JnEK.thumb.jpeg",
                "http://img5.duitang.com/uploads/item/201406/10/20140610085447_zeXJU.thumb.jpeg",
                "http://img4.duitang.com/uploads/blog/201406/08/20140608193617_HyFrY.thumb.jpeg",
                "http://img4.duitang.com/uploads/item/201405/30/20140530190040_KQdsM.thumb.jpeg",
                "http://cdn.duitang.com/uploads/item/201406/09/20140609101937_UBfJJ.thumb.jpeg",
                "http://img5.duitang.com/uploads/blog/201406/10/20140610170410_cFhwW.thumb.jpeg",
                "http://img5.duitang.com/uploads/item/201406/09/20140609225334_PdGwG.thumb.jpeg",
                "http://img4.duitang.com/uploads/blog/201406/09/20140609184438_e33i2.thumb.jpeg",
                "http://img5.duitang.com/uploads/blog/201405/29/20140529200010_AfL8f.thumb.jpeg",
                "http://img4.duitang.com/uploads/blog/201406/08/20140608104649_KVtMx.thumb.png",
                "http://img5.duitang.com/uploads/item/201406/01/20140601215152_wi4wf.thumb.jpeg",
                "http://cdn.duitang.com/uploads/blog/201406/08/20140608194234_FEGkW.thumb.jpeg",
                "http://img4.duitang.com/uploads/item/201405/31/20140531221002_Awtv8.thumb.jpeg",
                "http://img5.duitang.com/uploads/item/201406/08/20140608091030_TJ3Cc.thumb.jpeg",
                "http://cdn.duitang.com/uploads/item/201405/31/20140531221355_cSCTt.thumb.jpeg",
                "http://cdn.duitang.com/uploads/item/201406/08/20140608005415_arBdK.thumb.jpeg",
                "http://img5.duitang.com/uploads/item/201406/08/20140608000002_2MTjn.thumb.jpeg",
                "http://img5.duitang.com/uploads/item/201406/03/20140603012613_z88sn.thumb.jpeg",
                "http://cdn.duitang.com/uploads/item/201405/31/20140531221745_rnAzU.thumb.jpeg",
                "http://img5.duitang.com/uploads/item/201405/31/20140531220735_XBxFP.thumb.jpeg",
                "http://cdn.duitang.com/uploads/blog/201406/08/20140608194112_uEYf5.thumb.jpeg",
                "http://img5.duitang.com/uploads/blog/201406/08/20140608225626_xc2QT.thumb.jpeg",
                "http://img5.duitang.com/uploads/item/201406/07/20140607235759_sNS5Z.thumb.jpeg",
                "http://img5.duitang.com/uploads/item/201405/31/20140531220635_Lrw3w.thumb.jpeg",
                "http://cdn.duitang.com/uploads/item/201406/08/20140608004245_jmBmP.thumb.jpeg",
                "http://img4.duitang.com/uploads/item/201406/08/20140608020213_SBfGH.thumb.jpeg",
                "http://cdn.duitang.com/uploads/item/201406/08/20140608214105_kvVVY.thumb.jpeg",
                "http://img5.duitang.com/uploads/item/201406/03/20140603001556_XsMEv.thumb.jpeg",
                "http://cdn.duitang.com/uploads/item/201406/08/20140608024120_XjjGB.thumb.jpeg",
        };
}
