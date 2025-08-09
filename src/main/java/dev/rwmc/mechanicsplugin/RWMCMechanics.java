package dev.rwmc.mechanicsplugin;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import dev.rwmc.mechanicsplugin.listeners.EntityRegainHealthListener;
import dev.rwmc.mechanicsplugin.listeners.PlayerListener;
import dev.rwmc.mechanicsplugin.tasks.ActionBarTask;
import dev.rwmc.mechanicsplugin.tasks.HungerLockTask;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class RWMCMechanics extends JavaPlugin {

    public Logger Logger;

    @Override
    @SuppressWarnings("UnstableApiUsage")
    public void onEnable() {
        Logger = this.getLogger();
        Logger.log(Level.INFO, "RWMC Mechanics is enabled!");

        BukkitScheduler scheduler = this.getServer().getScheduler();
        scheduler.runTaskTimer(this, new ActionBarTask(this), 0, 20);
        scheduler.runTaskTimer(this, new HungerLockTask(this), 0, 20);
        getServer().getPluginManager().registerEvents(new EntityRegainHealthListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);

        LiteralCommandNode<CommandSourceStack> slugcatsCommand = Commands.literal("food")
                .requires(sender -> sender.getSender().hasPermission("rwmc.commands.food"))
                .then(Commands.literal("set")
                        .requires(sender -> sender.getSender().hasPermission("rwmc.commands.food.set"))
                        .then(Commands.argument("food", DoubleArgumentType.doubleArg(0))
                        .executes(ctx -> {
                            CommandSender sender = ctx.getSource().getSender();
                            Entity executor = ctx.getSource().getExecutor();

                            double food = ctx.getArgument("food", double.class);

                            if (!(executor instanceof Player)) {
                                sender.sendMessage(Component.text("Invalid target for setting food pips.", NamedTextColor.RED));
                                return Command.SINGLE_SUCCESS;
                            }

                            food = Math.min(food, Utilities.getPlayerMaxFood((Player) executor));
                            Utilities.setPlayerFood((Player) executor, food);

                            return Command.SINGLE_SUCCESS;
                        }))
                ).build();
        //noinspection CodeBlock2Expr
        this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands -> {
            commands.registrar().register(slugcatsCommand);
        });
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
