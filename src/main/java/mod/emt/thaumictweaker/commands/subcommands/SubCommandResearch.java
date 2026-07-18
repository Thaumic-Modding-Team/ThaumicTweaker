package mod.emt.thaumictweaker.commands.subcommands;

import com.google.common.collect.Lists;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import thaumcraft.api.capabilities.ThaumcraftCapabilities;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategory;
import thaumcraft.api.research.ResearchEntry;
import thaumcraft.common.lib.CommandThaumcraft;
import thaumcraft.common.lib.research.ResearchManager;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SubCommandResearch implements ISubCommand {
    @Override
    public String getSubCommand() {
        return "research";
    }

    @Override
    public int getMinCommandLength() {
        return 2;
    }

    @Override
    public int getMaxCommandLength() {
        return 4;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
        if(args.length == 2) {
            List<String> list = Lists.newArrayList("reload", "list");
            list.addAll(CommandBase.getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames()));
            return list;
        } else if(args.length > 2 && !args[1].equalsIgnoreCase("reload") && !args[1].equalsIgnoreCase("list")) {
            if(args.length == 3) {
                return Lists.newArrayList("add", "remove", "list", "reset", "all");
            } else if(args.length == 4 && (args[2].equalsIgnoreCase("add") || args[2].equalsIgnoreCase("remove"))) {
                Set<String> researchSet = new HashSet<>();
                for(ResearchCategory category : ResearchCategories.researchCategories.values()) {
                    for(ResearchEntry entry : category.research.values()) {
                        String key = entry.getKey();
                        if(args[3] != null && !args[3].isEmpty()) {
                            if(key.toLowerCase().startsWith(args[3].toLowerCase())) {
                                researchSet.add(key);
                            }
                        } else {
                            researchSet.add(key);
                        }
                    }
                }
                return CommandBase.getListOfStringsMatchingLastWord(args, Lists.newArrayList(researchSet));
            }
        }
        return Collections.emptyList();
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if(args.length == 2) {
            String arg = args[1];
            if(arg.equalsIgnoreCase("reload")) {
                for(ResearchCategory category : ResearchCategories.researchCategories.values()) {
                    category.research.clear();
                }
                ResearchManager.parseAllResearch();
                sender.sendMessage(new TextComponentTranslation("§5Success!"));
            } else if(arg.equalsIgnoreCase("list")) {
                this.listAllResearch(sender);
            } else {
                throw new CommandException(new TextComponentTranslation("command.thaumictweaker:research.invalid").getFormattedText());
            }
        } else {
            EntityPlayer player = CommandBase.getPlayer(server, sender, args[1]);
            if (args.length == 3) {
                String arg = args[2];
                if (arg.equalsIgnoreCase("list")) {
                    this.listAllResearch(sender, player);
                } else if (arg.equalsIgnoreCase("reset")) {
                    this.resetResearch(sender, player);
                } else if (arg.equalsIgnoreCase("all")) {
                    this.addAllResearch(sender, player);
                } else {
                    throw new CommandException(new TextComponentTranslation("command.thaumictweaker:research.invalid").getFormattedText());
                }
            } else if (args.length == 4) {
                String arg = args[2];
                if (arg.equalsIgnoreCase("add")) {
                    this.addResearch(sender, player, args[3]);
                } else if (arg.equalsIgnoreCase("remove")) {
                    this.removeResearch(sender, player, args[3]);
                } else {
                    throw new CommandException(new TextComponentTranslation("command.thaumictweaker:research.invalid").getFormattedText());
                }
            }
        }
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return index == 2 && args.length > 2 && !args[1].equalsIgnoreCase("reload") && !args[1].equalsIgnoreCase("list");
    }

    private void listAllResearch(ICommandSender sender) {
        for(ResearchCategory cat : ResearchCategories.researchCategories.values()) {
            for(ResearchEntry entry : cat.research.values()) {
                sender.sendMessage(new TextComponentTranslation("§5" + entry.getKey()));
            }
        }
    }

    private void listAllResearch(ICommandSender sender, EntityPlayer player) {
        StringBuilder builder = new StringBuilder();
        for(String key : ThaumcraftCapabilities.getKnowledge(player).getResearchList()) {
            if (builder.length() > 0) {
                builder.append(", ");
            }
            builder.append(key);
        }
        sender.sendMessage(new TextComponentTranslation("§5Research for " + player.getName()));
        sender.sendMessage(new TextComponentTranslation("§5" + builder));
    }

    private void addResearch(ICommandSender sender, EntityPlayer player, String research) {
        if (ResearchCategories.getResearch(research) != null) {
            CommandThaumcraft.giveRecursiveResearch(player, research);
            ThaumcraftCapabilities.getKnowledge(player).sync((EntityPlayerMP) player);
            player.sendMessage(new TextComponentTranslation("§5" + sender.getName() + " gave you " + research + " research and its requisites."));
            sender.sendMessage(new TextComponentTranslation("§5Success!"));
        } else {
            sender.sendMessage(new TextComponentTranslation("§cResearch does not exist."));
        }
    }

    private void addAllResearch(ICommandSender sender, EntityPlayer player) {
        for(ResearchCategory cat : ResearchCategories.researchCategories.values()) {
            for(ResearchEntry ri : cat.research.values()) {
                CommandThaumcraft.giveRecursiveResearch(player, ri.getKey());
            }
        }
        player.sendMessage(new TextComponentTranslation("§5" + sender.getName() + " has given you all research."));
        sender.sendMessage(new TextComponentTranslation("§5Success!"));
    }

    private void removeResearch(ICommandSender sender, EntityPlayer player, String research) {
        if (ResearchCategories.getResearch(research) != null) {
            CommandThaumcraft.revokeRecursiveResearch(player, research);
            ThaumcraftCapabilities.getKnowledge(player).sync((EntityPlayerMP) player);
            player.sendMessage(new TextComponentTranslation("§5" + sender.getName() + " removed " + research + " research and its children."));
            sender.sendMessage(new TextComponentTranslation("§5Success!"));
        } else {
            sender.sendMessage(new TextComponentTranslation("§cResearch does not exist."));
        }
    }

    private void resetResearch(ICommandSender sender, EntityPlayer player) {
        ThaumcraftCapabilities.getKnowledge(player).clear();
        for(ResearchCategory cat : ResearchCategories.researchCategories.values()) {
            for(ResearchEntry ri : cat.research.values()) {
                if (ri.hasMeta(ResearchEntry.EnumResearchMeta.AUTOUNLOCK)) {
                    ResearchManager.completeResearch(player, ri.getKey(), false);
                }
            }
        }
        player.sendMessage(new TextComponentTranslation("§5" + sender.getName() + " has reset all your research."));
        sender.sendMessage(new TextComponentTranslation("§5Success!"));
        ThaumcraftCapabilities.getKnowledge(player).sync((EntityPlayerMP) player);
    }
}
