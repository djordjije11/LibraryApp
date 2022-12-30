package services;

import models.Member;
import tcp.TcpClient;

/**
 *
 * @author Djordjije
 */
public class MemberService extends EntityService<Member> {
    public MemberService(TcpClient tcpClient) {
        super(tcpClient);
    }
}
