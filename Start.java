
interface IMemberOperation {
    double discountedFee(); 
}

abstract class Member {
    protected String memberID;
    protected double monthlyFee;

    public Member(String memberID, double monthlyFee) {
        this.memberID = memberID;
        this.monthlyFee = monthlyFee;
    }

    public abstract void showInfo();
}


class PlatinumMember extends Member implements IMemberOperation {
    private int freeSessions;

    public PlatinumMember(String memberID, double monthlyFee, int freeSessions) {
        super(memberID, monthlyFee);
        this.freeSessions = freeSessions;
    }

    @Override
    public double discountedFee() {
        if (monthlyFee > 8000) {
            return monthlyFee * 0.92; 
        }
        return monthlyFee;
    }

    @Override
    public void showInfo() {
        System.out.println("Platinum Member ID: " + memberID);
        System.out.println("Monthly Fee: " + monthlyFee);
        System.out.println("Free Sessions: " + freeSessions);
        System.out.println("Discounted Fee: " + discountedFee());
    }
}


class StandardMember extends Member implements IMemberOperation {
    private boolean groupClassAccess;

    public StandardMember(String memberID, double monthlyFee, boolean groupClassAccess) {
        super(memberID, monthlyFee);
        this.groupClassAccess = groupClassAccess;
    }

    @Override
    public double discountedFee() {
        if (monthlyFee > 8000) {
            return monthlyFee * 0.92; 
        }
        return monthlyFee;
    }

    @Override
    public void showInfo() {
        System.out.println("Standard Member ID: " + memberID);
        System.out.println("Monthly Fee: " + monthlyFee);
        System.out.println("Group Class Access: " + (groupClassAccess ? "Yes" : "No"));
        System.out.println("Discounted Fee: " + discountedFee());
    }
}


class Gym {
    private String name;
    private Member[] mm;
    private int memberCount;

    public Gym(String name, int count) {
        this.name = name;
        this.mm = new Member[count];
        this.memberCount = 0;
    }

    public void addMember(Member m) {
        if (memberCount < mm.length) {
            mm[memberCount] = m;
            memberCount++;
            System.out.println("Member " + m.memberID + " added successfully.");
        } else {
            System.out.println("Gym is full! Cannot add more members.");
        }
    }

    void removeMember(String id) {
        for (int i = 0; i < memberCount; i++) {
            if (mm[i].memberID == id) {  
                for (int j = i; j < memberCount - 1; j++) {
                    mm[j] = mm[j + 1];
                }
                mm[memberCount - 1] = null;
                memberCount--;
                System.out.println("Removed: " + id);
                return;
            }
        }
        System.out.println("Not found!");
    }

    public void showMembers() {
        System.out.println(" " + name + " Gym Members ");
        if (memberCount == 0) {
            System.out.println("No members in the gym.");
        } else {
            for (int i = 0; i < memberCount; i++) {
                mm[i].showInfo();
            }
        }
    }

    public void totalRevenue() {
        double total = 0;
        for (int i = 0; i < memberCount; i++) {

            IMemberOperation memberOp = (IMemberOperation) mm[i];
            total += memberOp.discountedFee();
        }
        System.out.println("Total Monthly Revenue (after discounts): $" + total);
    }
}

public class Start {
    public static void main(String[] args) {
        
        Member m1 = new PlatinumMember("PLT001", 20000, 5);
        Member m2 = new StandardMember("STD001", 8000, true);
        Member m3 = new StandardMember("STD002", 9000, false);

        
        Gym gym = new Gym("FitZone", 10);

        
        System.out.println(" Adding Members ");
        gym.addMember(m1);
        gym.addMember(m2);
        gym.addMember(m3);

       
        gym.showMembers();

        
        System.out.println(" Revenue ");
        gym.totalRevenue();

        
        System.out.println(" Removing Member ");
        gym.removeMember("STD001");
        gym.showMembers();

        
        System.out.println(" Revenue After Removal");
        gym.totalRevenue();
    }
}