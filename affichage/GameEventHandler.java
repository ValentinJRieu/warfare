package wargame.affichage;

import javax.swing.event.MouseInputListener;
import java.awt.event.*;

public class GameEventHandler implements MouseMotionListener, MouseWheelListener, MouseInputListener, KeyListener {
    Game game;
    RCPosition oldOvered;
    GameEventHandler(Game game){
        this.game = game;
    }
    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println("keyPressed");
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                game.up();
                break;
            case KeyEvent.VK_DOWN:
                game.down();
                break;
            case KeyEvent.VK_RIGHT:
                game.right();
                break;
            case KeyEvent.VK_LEFT:
                game.left();
                break;
        }
    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {}

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1)
            System.out.println(game.getIJFromXY(e.getX(), e.getY()));
        if(e.getButton() == MouseEvent.BUTTON3)
            System.out.println(game.getiStart() + " " + game.getiEnd() + " " + game.getjStart() + " " + game.getjEnd());
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        //System.out.println(game.getIJFromXY(e.getX(),e.getY()));

    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        game.requestFocus();
    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button is pressed on a component and then
     * dragged.  {@code MOUSE_DRAGGED} events will continue to be
     * delivered to the component where the drag originated until the
     * mouse button is released (regardless of whether the mouse position
     * is within the bounds of the component).
     * <p>
     * Due to platform-dependent Drag&amp;Drop implementations,
     * {@code MOUSE_DRAGGED} events may not be delivered during a native
     * Drag&amp;Drop operation.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    /**
     * Invoked when the mouse cursor has been moved onto a component
     * but no buttons have been pushed.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        game.overedCase = game.getIJFromXY(e.getX(),e.getY());
        game.repaint();
        /*RCPosition rp = game.getIJFromXY(e.getX(),e.getY());
        if(rp != null){
            if(oldOvered != null){
                if(rp.i != oldOvered.i || rp.j != oldOvered.j){
                    Case c = game.ct.getCase(rp.j,rp.i);
                    c.selected = true;
                    c = game.ct.getCase(oldOvered.j,oldOvered.i);
                    c.selected = false;
                }
            }else{
                Case c = game.ct.getCase(rp.j,rp.i);
                c.selected = true;
            }
            oldOvered = rp;
        }
        game.repaint();*/
    }

    /**
     * Invoked when the mouse wheel is rotated.
     *
     * @param e the event to be processed
     * @see MouseWheelEvent
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if(e.getWheelRotation()<0){
            game.zoom();
        }else{
            game.deZoom();
        }
    }
}
