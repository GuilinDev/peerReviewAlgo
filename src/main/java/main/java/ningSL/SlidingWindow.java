package main.java.ningSL;

import java.util.HashMap;

public class SlidingWindow {
    public static void main(String[] args) {
       String t = "ckzviabspcfbabslodcxtzanlsnwbqrozvnfadhtskosxhxaxzwcthvirwivsfuyxgkdvvdmmaoohvnfegkzdajhzibfiuxvsihpseyyiapmgldyojfselzzudzrxcksvfoqoeimyfhnvidryqhhpvjawwpzspcfnvmewnhcbbbwfjifmcuspbrrjyhcusyiuydscwenyicdlfzaoaiudsdyjmhmzwmeaozheiaddevjrrgsfgqnyeoyxfvktctazsfizkynlookeorzmdvtloyfpbqjgzhjvykdthwkuubnbalrddjxpizysaptrinlytecouekkpsfzbjaitckodcdinydtaaakbntwbvrcabylllxtgtkdqfkjggvbrnxnvcsfpcutabvazyeqzitpwhhdpcxaklhbjrcsqzveytzbgoeqnyrbkvkfnlqhrnedjomfmkuadwabnzomgvtdypocbvipryedcxnrcrqwpefqducxxilhpoewlliilidxucjydpahcumngpekrlroftwpbaejfjmohnkulxdglcpcszpyqestonhxskshzmdkikjkeymddyiysmigvqjveocbabhcofwyseytwfgiqmufcqrugwofytxzwwwiwlxdshtamwneosotqbfjjdxmhjkmzcvpmkuumycqzvlmwvjdwkcikyewupwaotrseyomhuykdkohveftgqfoqjcnstlxdasnvimslmuqsqrhvbukyfvpswmlavtxegbcesxgzswwnslxymmrdwmpalcazukvyyotlvwvlishtgbmlznrmcjysjemwqfjgbehnowgjlvtgemlkkdqpxiuqwhqkqdkexoflxfescoblknurkntbpfqfiervqgeiasguycmjoyzyujgzdfjobrswjpxixmadnnkdtazbasmnnlbubokkvomtupdpqwnoddummmqbwbthfhqdgdawdwxlmgvelhefvqcrpxbkgvhyrpxmfivrvkkqfpxjhzqesiqoeanqzpvbgonwptzqgtajoiiphivxtefarwjyoklkxumrndswebdsgiipzrcunpnibmxwlkihnzlswaujflztxxnuhojrpkzldwhnuiizxalawskkvaplopthvzjrbqfwpwjfkzrhyciajdsaeusctnvajuubjiqdqsaqjxtxgxabpdwxzgiedyyeosfdbkairekywkilyksjratftctphompqoomxucysbzrpywjzumoiizklkooilgeapimiixawfgiszfqucihxfrtrwijyeiebudncwnxbnafohhhqaltksafjfegxlbbewetsyrtuxxlngffpcnyfdarqwznsuiuhvqshjpvbiqkxgfwyrkdlorlwgexfinunbfzifxlfufalbkyfdekhupuuflsetpbppaahwbtjpjguygpnavimbbaeikwjxurdxyfgmldgdbkyxzhvwlxzlpnpmzlyosvhngmuqkrvcsabhiahhguxuakphuaptbjfpxybqnlfazkagwlbvzcnzorpymafiwvtkjtilwihwoyelogtpdyipzkurqcghpdqgrfpnmrmahbkbyupxwooblaydongjrlnuhoikoyiexgzlzgonceyhfwypsgypilxcnkdhxvbfddnhvdicolrjyzttrymmcvoyswaszbbugsuuewhewtoxgnrbfqsysjqqcutoikssesxaohxxfxofiguvpgzfkimmqdjuyljlpndapzygidopwxnaetmmpyaqozybbpfqlonebiilvckmtyaommksfcrmdeyjjbjncmpruofaaccnvndkovjugcrzcwkbdhqdiwnvnavnngloshyataygyukrecamzftmeephucmofjgrzsexsprpdkazhmtaepqppgzjmxxzlpbkouqlhqcxdpaslrjrsnfbjvbekpyqsldzhxarpzbbjteudfwfjdipgcdwylorxivwbjkegazpcngzkokygjfnmfeumbetznsxsmyhccurqdzznvhrtawpklrrrbedqzkiczignlaoaiydezgktdecaxwxxecymbuisvhlcjlhmnpjuegnaawfxopvvkxihehquxzlveabigomeptsqbfurytjikpbtsotgfghadilylnimxcsvgtmzjgxjyhabtfawzmomctquctwnolglflghdugeutgdmkitunbkhgoceqyrzvwprotiysaiqcnwxflcjgjqobeskizjqqwihnmxyvbeeufyvouupnyuuyauxtmhtqlbgoacjdkvmuqohbkpctfwxvwlghevsuozrgxkrrrllnvjloeligxvnvzluxlajszgeilxdjaviawhlthtxuclqypgdiwgwrhynmzqghyzeednlmyepgxxvtmjsuprisbgyvcojhculwdkjmpmvueaftiujrmdqptrmlrwdyjtjlznuhftzmffbcqsjemrpjrqxtuxhlnqvckgcuvhswgtxonfljjwnexdafpjzwjdypdfwhwogikkpsvuckmgehcqmdecriamdqsdsxuhsdqenrpifzjnpvrbpvetatbmvsncltabzspzedumeclbxiqnsmdkbqfedikoalfsnxkhewwzyeqxlecfimvdpxdnvrtlxlxzakupknvchursvilyjyfxmvdeojclhjjwxtpqiogsfdnzchrlztvxmibbociiberaevpjmyqxvcicxuwkmyjlgxhvrjdqbjzdctzwidoplkvazhgsdbkavmrpfjgebydmloykqtxocdhcpwrtvawhofvwnohtwodppmcrbhvrvexzijiwgkptntyljvikjlzpjvpxaejfwzyldxrckbzylvhpqmjjeyatmrbcgozvcalcctngwuysjqmkmhjwmyyfxhdjswimahqzptrphtjekkabpgopfdnhwaclxzavftmswadgztxceonrkczurtdvxhabiafyzdbdbyorerekjzgpueihiamlscdndjjgqwdjsmeweqrfpypfrfsciclvrrqchnmtxscbvbbipzingkeopuvlnpqaeqidlpvusbkjhntvxbookqctymjmhqjvsnaiblmnofxphanpxspmkmxmyejooqpytdixgguonbpaipurpwwfqxrrtekvvtpgndabbvjcoxonxciuypxqcfolxirvafnmgsrcrenwyuxfekuqyfdtvhvmonvkstbmvygsbguiknltpixsebxktczniurulxpyxckrtmhpvgqpbvlqwvcqwaocalqtdwomgrjyvryodeikbeeebcrrkugliqgktoohsneynmfafykpmndfgwtpdwfrmicenyntbfyyntlqqrujfioycrhopgiadmsjcycucyuqvzufbvachmlwyfxspzrvkkvkhuykoqrwshzmyypmvosuezpsgtqtecayj";
       String s = "livsugnixbcmmajhinduvzfjzxtzneixibupxfezifaovcbowfayjtlcdsjogjytuczxbwluiktkdyecquebuvkgfobhpiqfbyckfbchvfkdowckrvuiblhbmubugrrovdhbyvevwetlusthiyxgtaqcdfytgbkrnnjifdslzntgnryqcmwkumdvqctymyffanmawobfihsfqmtqvivvouunsexpzcfmiituppwxgjyspyoeuspyystyneoonatbojiufzhhfeakqzeypaopkyscadbkzsnnjewgflysazhwaigicrzeccanemfmrvrdzxtcbuqvjblrynlhbmtkzhtiqnhrfclxovtijwucgvsreirqmhweoazxdyydksrchvizmljhdkekaxbqkleaolqnepijxpwbcxfadfsxswhzmmffsmoiyfosbwjjcgqpexddwizaiqpsaagwxkajdhqeyouihwlfrmnelakembxwcrvwqyaguvqewjczfmfqiqmjbqxcmfrpqoewszkomwckfgrlkannjefxsulbpkpxlvbrgwruvhdonuezoomvnyridrosfzhfvhmqbzdsfvjgfdqvafheuhveaqwrofhgkfobkkevdsqyumzrvapfjypvjsdzaxxwcnsbxasqewmkmucxbgjqntdsvrbndebttkoiznatcjgtgklljurkkdvpbrtbolvgrqwsnuoubhgvtmbakwgvxypndpqtireuvbdxfjcioqqsbfrqahqsfbwfnlkwobnqdbptxrizourrxyjejflgkbcwmrtttqeeqkyzrzdcfakwcfajhjfmcpnsbekwtzgpbwrotgsxopaemsnywoxsqxoyricgosfurfggulchgyoocivnkqytgbroijmaduoywbudtgwugekikebdadoygtmikcficifdcsvritesbvznlbrgbveudwzatarhbehowrrakimocgqyxzedhlqxzxtqloupbwbdvewcbehkmoahoykouhilivtxukqhwmyjjqmfxqybahsubfvnhctudtpxwsnwfmsamavjkqiabzzcqgqssygswavqpdtxfyognqybsogsbkzkktfjqebhitvqnawokacbkfkthqoammdscyvxgaaqyaplexhlwweambfwtylvihpdedshaeluxgthrejkgkvwrtyzpuygqghtlkmpnzkyjiwyuaqijkxttvdsxddfkmgnnlmamjumsbjmjxqkrgncrwyoyetdsmtanoasirkfklcsxukfdebnlbgobwgyqrthzwjbwpxuxmheplqyjaucgrptfewjahejsfypktnpafmlrzekbjqsgthrwbdxttitsqyglsjgnzmscncgvkooxdlxsmmugeeayicndgoagpfckerzuwdphqksijniyckzviabspcfbabslodcxtzanlsnwbqrozvnfadhtskosxhxaxzwcthvirwivsfuyxgkdvvdmmaoohvnfegkzdajhzibfiuxvsihpseyyiapmgldyojfselzzudzrxcksvfoqoeimyfhnvidryqhhpvjawwpzspcfnvmewnhcbbbwfjifmcuspbrrjyhcusyiuydscwenyicdlfznoaiudsdyjmcmzwmeaozheiaddevjrrgsfgqnyeoyxfvktctazsfizkynlookeorzmdvtloyfpbqjgzhjvykdthwkuubnbalrddjxpihysaptrinlytecouekkpsfzbjaitckodcdinydtaaakbntwbvrcasylllxtgtkdqfkjggvbanxnvcsfphutabvazyeqzitpwhhdpcxaklhbjrcsqzveytzbgoeqnyrbkvkfnlqhrnedjomfmkuadwabnzomgvtdypocbvipryedcxnrcrqwpefqducxxilhpoewlliilidxucjydpahcumagpekrlroftwpbaejfjmohnkulxdglcpcszpyqestonhxskshzmdkikjkeymdfyiysmigvqjveocbabhcofwyseytwfgiqmufcqrugwofytxzwwwiwlxdshtamwneosotqbfjjdxmhjkmzcvpmkuumycqzvlmwvjdwkcityewupwaotrseyomhuykdkohveftgqfoqjcnstlxdasnvimslmuqsqrhvbukyfvpswmlavtxegbcesxgzswwnslxymmrdwmpalcazukvyyotlvwvlishtgbmlznrmcjysjemwqfjgbehnowgjlvtgemlkkdqpxiuqwhqkqdkexoflxfescoblknurkntbpfqfiervqgeiesguycmjoyzyujgzdfjobrswjpxixmadnnkdtazbasmnnlbubokkvomtupdpqwnoddummmqbwbthfhqdgdawdwxlmgvelhefvqcrpxbkgvhyrpxmfivrvkkqfpxjhzqesiqoeanqzpvbgonwptzqgtajoiiphivxtefarwjyoklkxumrndswebdsghipzrcunpnibmxwlkihnzlswaujflztxxnuhojrpkzldwhnuiizxalawskkvaplopthvzjrbqfwpwjfkzrhyciajdsaeusctnvajuubjiqdqsaqjxtxgxabpdwxzgiedyyeosfdbkrirekywkilyksjratftctphompqoomxucysbzrpywjzumoiizklkooilgeapimiixawfgiszfqucihxfrtrwijyeiebudncwnxbnafohhhqaltksafjfegxlbbewetsyrtuxxlngffpcnyfdarqwznsuiuhvqshjpvbiqkxgfwyrkdlorlwgexfinunbfzifxlfufalbkyddekhupuuflsetpbppaahwbkjpjguygpnavimbbaeikwjxurdxyfgmldgdbkyxzhvwlxzlpnpmzlyosvhngmuqkrvcsabhiahhguxuakphuaptbjfpxybqnlfazkagwlbvzcnzorpymafiwvtkjtilwihwoyelogtpdyipzkurqcghpdqgrfpnmrmahbkbyupxwooblaydongjrlnuhoikoyiexgzlzgonceyhfwypsgypilxcnkdhxvbfddnhvdicolrjyzttrymmcvoyswaszbbugsuuewhawtoxgnrbfqsysjqqcutoikssesxaohxxfxofiguvpgzfkimmqdjuyljlpndapzygidopwxnaetmmpyaqozybbpfqlonebiilvckmtyaommksfcrmdeyjjbjncmpruofaaccnvndkovjugcrzcwkbdhqdiwnvnavnngloshyataygyukrecamzftmeephucmofjgrzsepsprpdkazhmtaepqppgzjmxxzlpbkouqlhqcxdpaslrjrsnfbjvbekpyqsldzhxarpzbbjteudfwfjdipgcdwylorxivwbjkegazpcngzkokygjfnmfeumbetznsxsmyhccurqdzznvhrtawpklrrrbedqzkiczignlaoaiydezgktdecaxwxxecymbuisvhlcjlhmnpjuegnaawfxopvvkxihehquxzlveabigomeptsqbfurytjikpbtsotgfghadilylnimxcsvgtmzjgxjyhabtfawzmomctquctwnolglflghdugeutgdmkitunbkhgoceqyrzvwprotiysaiqcnwnflcjgjqobeskizjqqwihnmxyvbeeufyvouupnyuuyauxtmhtqlbgoacjdkvmuqohbkpctfwxvwlgievsuozrgxkrrrllnvjloeligxvnvzluxlajszgeilxdjaviawhlthtxuclqypgdiwgwrhynmzqghyzeednlmyepgxxvtmjsuprisbgyvcojhculwdkjmpmvueaftiujrmdqptrmlrwdyjtjlznuhftzmffbcqsjemrpjrqxtuxhlnqvckgcuvhswgtxonfljjwnexdafpjzwjdypdfwhwogikkpsvuckmgehcqmdecriamdqsdsxuhsdqenrpifzjnpvrbpvetatbmvsncltabzspzedumeclbxiqnsmdkbqfedikoalfsnxkhewwzyeqxlecfimvdpxdnvrtlxlxzakupknvchursvilyjyfxmvdfojclhjjwxtpqiogsfdxzczrlztvxmibbociiberaevpjmyqxvcicxuwkmyjlgxhvrjdqbjzdctzwidoplkvazhgsdbkavmrpfjgebydmloykqtxocdhcpwrtvawhofvwnohtwodxpmcrbhvrvexzijiwgkptntyljvikjlzpjvpxaejfwzyldxrckbzylvhpqmjjeyatmrbcgozvcalcctngwuysjqmkmhjwmyyfxhdjswimahqzptrphtjekkabpgopfdnhwaclxzavftmswadgztxceonrkczurtdvxhabiafyzdbdbyorerekjzgpueihiamlscdndjjgqwdjsmeweqrfpypfrfsciclvrrqchnmtxscbvbbipzingkeopuvlnpqaeqidlpvusbkjhntvxbookqctymjmhqjvsnaiblmnofxphanpxspmkmxmyejooqpytdixgguonbpaipurpwwfqxrrtekvvtpgndabbvjcoxonxciuypxqcfolxirvafnmgsrcrenwyuxeekuqyfdtvhvmonvkbtbmvygsbguiknltpixsebxktczniurulxpyxckrtmhpvgqpbvlqwvcqwaocalqtdwomgrjyvryodeikbeeebcrrkugliqgktoohsneynmfafykpmndfgwtpdwfrmicenyntbfyyntlqqrujfioycrhopgiadmsjcycucyuqvzufbvachmlwyfxspzrvkkvkhuykoqrwshzmyypmvosuezpsgtqtecayjdasrysrmdewxmqntqmuavczczrcrzokjzqtiucdfmijfqkizfftxsddkdwlofkjbuhzodgzeslpezdifpdfktufgekrrbsxfeqduphoemwhncbblqkoyychuwlkjfxjmvdasfhwxnxjmstabgiujditpmpnojsfzpgqvupkdipphutiewfynvoxmdaloyxsksaxnyyizreshgejskhokarxddrrkoeglwledovrbarhkfcetrshiuucmbrtqjawsavbhuoijshkzcmogbjurybhwduushlmhjzgthhlvftpytayjzzbcxbtvozwhsmtkltgrugyfaoldnnswbohpsqvwbwtjcxeewsvfwpkwltxmomlwsylwvcmmrwjvvbbktqxnypfmhjigzaqacmprjfnooinkgbjzzwyuafxsmqdcmsxhbfoejjzxizufvfejrrcfaijqovlxdngkgwisvryevivawbcttftuxympktxbxotvdycxhfokmtdeqacdceffvhvjpnzfuhqslixvlcxehvbkritokoqqlbmkdkgflthhhdgiaidjjpvcatctiosolfqzcjmdppttztpklgosnshspna";

       //  System.out.println(checkInclusion(t,s));
        System.out.println("String:" + minWindow(s,t));
    }
    public static String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }
        HashMap<Character, Integer> window = new HashMap<>();
        HashMap<Character, Integer> need = new HashMap<>();
        HashMap<Character, Integer> test = new HashMap<>();
        for (char t1 : t.toCharArray()) {
            need.put(t1, need.getOrDefault(t1,0) + 1);
        }
        for (char s1 : s.toCharArray()) {
            test.put(s1, test.getOrDefault(s1,0) + 1);
        }
        int left = 0; int right = 0; int valid = 0;
        int start = 0; int len = Integer.MAX_VALUE; // use one minRight to record
        while (right < s.length()) { // 区间[left, right)是左闭右开的，所以初始情况下窗口没有包含任何元素：
            char s1 = s.charAt(right);
            right ++;
            if (need.containsKey(s1)) {
                window.put(s1,window.getOrDefault(s1,0) + 1);
                if (window.get(s1).equals(need.get(s1))){
                    valid ++;
                }
            }

            // 右指针移动当于在寻找一个「可行解」，然后移动左指针在优化这个「可行解」，最终找到最优解
            while (valid == need.size()) {
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                char s2 = s.charAt(left);
                left ++;
                if (need.containsKey(s2)) { // every time need containsKey not contains
                    if (window.get(s2).equals(need.get(s2))){
                        valid --;
                    }
                    window.put(s2,window.get(s2) - 1);
                }

            }
        }
        if (len == Integer.MAX_VALUE) return ""; // don't miss
        return s.substring(start, start + len);
    }


        public static boolean checkInclusion(String t, String s) {

            HashMap<Character, Integer> window = new HashMap<>();
            HashMap<Character, Integer> need = new HashMap<>();

            for (char t1 : t.toCharArray()) {
                need.put(t1, need.getOrDefault(t1,0) + 1);
            }
            int left = 0; int right = 0; int valid = 0;
            while (right < s.length()) { // 区间[left, right)是左闭右开的，所以初始情况下窗口没有包含任何元素：
                char s1 = s.charAt(right);
                right ++;
                if (need.containsKey(s1)) {
                    window.put(s1,window.getOrDefault(s1,0) + 1);
                    if (window.get(s1) == need.get(s1)){
                        valid ++;
                    }
                }

                // 右指针移动当于在寻找一个「可行解」，然后移动左指针在优化这个「可行解」，最终找到最优解
                while (right - left >= t.length()) {
                    if (valid == need.size()) return true;
                    char s2 = s.charAt(left);
                    left ++;
                    if (need.containsKey(s2)) { // every time need containsKey not contains
                        if (window.get(s2) == need.get(s2)){
                            valid --;
                        }
                        window.put(s2,window.get(s2) - 1);
                    }
                }
            }
            return false;
        }

}
