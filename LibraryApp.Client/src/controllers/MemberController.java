package controllers;

import models.Member;
import tcp.TcpClient;

/**
 *
 * @author Djordjije
 */
public class MemberController extends EntityController<Member> {
    public MemberController(TcpClient tcpClient) {
        super(tcpClient);
    }
}
